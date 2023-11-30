package com.masofino.magecontroll.services;

import com.masofino.magecontroll.models.website.Website;
import com.masofino.magecontroll.repositories.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    public Page<Website> findAll(Pageable pageable) {
        return websiteRepository.findAll(pageable);
    }

    public Optional<Website> findById(int id) {
        return websiteRepository.findById(id);
    }

    public Website save(Website website) {
        return websiteRepository.save(website);
    }

    public void deleteById(int id) {
        websiteRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return websiteRepository.existsById(id);
    }
}
