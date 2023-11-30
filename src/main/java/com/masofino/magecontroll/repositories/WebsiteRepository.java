package com.masofino.magecontroll.repositories;

import com.masofino.magecontroll.models.website.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<Website,Integer> {
}
