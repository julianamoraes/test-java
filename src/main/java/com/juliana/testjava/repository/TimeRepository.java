package com.juliana.testjava.repository;

import com.juliana.testjava.domain.Time;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Time entity.
 */
@SuppressWarnings("unused")
public interface TimeRepository extends JpaRepository<Time,Long> {

}
