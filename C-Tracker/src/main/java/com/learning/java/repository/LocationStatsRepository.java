package com.learning.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.java.models.LocationStats;

@Repository
public interface LocationStatsRepository extends JpaRepository<LocationStats, Long> {
	
}
