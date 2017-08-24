package com.juliana.testjava.repository;

import com.juliana.testjava.domain.Campanha;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Campanha entity.
 */
@SuppressWarnings("unused")
public interface CampanhaRepository extends JpaRepository<Campanha,Long> {

}
