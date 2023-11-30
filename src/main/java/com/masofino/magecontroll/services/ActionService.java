package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.action.Action;
import com.masofino.magecontroll.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;

    public Page<Action> findAll(Pageable pageable) {
        return actionRepository.findAll(pageable);
    }

    public Optional<Action> findById(int id) {
        return actionRepository.findById(id);
    }

    public Action save(Action action) {
        return actionRepository.save(action);
    }

    public void deleteById(int id) {
        actionRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return actionRepository.existsById(id);
    }
}
