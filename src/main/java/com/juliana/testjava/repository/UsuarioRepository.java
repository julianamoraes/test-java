package com.juliana.testjava.repository;

import com.juliana.testjava.domain.Usuario;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Usuario entity.
 */
@SuppressWarnings("unused")
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("select distinct usuario from Usuario usuario left join fetch usuario.campanhas")
    List<Usuario> findAllWithEagerRelationships();

    @Query("select usuario from Usuario usuario left join fetch usuario.campanhas where usuario.id =:id")
    Usuario findOneWithEagerRelationships(@Param("id") Long id);

}
