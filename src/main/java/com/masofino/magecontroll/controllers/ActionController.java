package com.masofino.magecontroll.controllers;

import com.masofino.magecontroll.models.action.Action;
import com.masofino.magecontroll.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping
    public Page<Action> getAllActions(Pageable pageable) {
        return actionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Action> getActionById(@PathVariable int id) {
        Action action = actionService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Action not found");
                });

        return ResponseEntity.ok(action);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAction(@Validated @RequestBody Action action) {
        try {
            Action createdAction = actionService.save(action);
            return new ResponseEntity<>(createdAction, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Action.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Action> updateAction(@PathVariable int id, @RequestBody Action action) {
        return actionService.findById(id)
                .map(act -> {

                    if (action.getName() != null) {
                        act.setName(action.getName());
                    }
                    if (action.getPath() != null) {
                        act.setPath(action.getPath());
                    }
                    if (action.getDescription() != null) {
                        act.setDescription(action.getDescription());
                    }

                    return ResponseEntity.ok(actionService.save(act));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAction(@PathVariable int id) {
        return actionService.findById(id)
                .map(user -> {
                    actionService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
