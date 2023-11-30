package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.website.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/websites")
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @GetMapping
    public Page<Website> getAllWebsites(Pageable pageable) {
        return websiteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Website> getWebsiteById(@PathVariable int id) {
        Website website = websiteService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Website not found");
                });

        return ResponseEntity.ok(website);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWebsite(@Validated @RequestBody Website website) {
        try {
            Website createdWebsite = websiteService.save(website);
            return new ResponseEntity<>(createdWebsite, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Could not create Website.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Website> updateWebsite(@PathVariable int id, @RequestBody Website website) {
        return websiteService.findById(id)
                .map(web -> {

                    if (website.getName() != null) {
                        web.setName(website.getName());
                    }
                    if (website.getPath() != null) {
                        web.setPath(website.getPath());
                    }

                    return ResponseEntity.ok(websiteService.save(web));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWebsite(@PathVariable int id) {
        return websiteService.findById(id)
                .map(user -> {
                    websiteService.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
