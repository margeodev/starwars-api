package com.starwars.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starwars.api.model.Rebelde;

public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
	
	public List<Rebelde> findAllByTraidor(boolean traidor);
	
}
