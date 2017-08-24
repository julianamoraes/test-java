package com.juliana.testjava.web;

import com.juliana.testjava.domain.Campanha;

import com.juliana.testjava.repository.CampanhaRepository;
import com.juliana.testjava.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Campanha.
 */
@RestController
@RequestMapping("/api")
public class CampanhaResource {

    private final Logger log = LoggerFactory.getLogger(CampanhaResource.class);
        
    @Inject
    private CampanhaRepository campanhaRepository;

    /**
     * POST  /campanhas : Create a new campanha.
     *
     * @param campanha the campanha to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campanha, or with status 400 (Bad Request) if the campanha has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> createCampanha(@Valid @RequestBody Campanha campanha) throws URISyntaxException {
        log.debug("REST request to save Campanha : {}", campanha);
        if (campanha.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("campanha", "idexists", "A new campanha cannot already have an ID")).body(null);
        }
        Campanha result = campanhaRepository.save(campanha);
        return ResponseEntity.created(new URI("/api/campanhas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("campanha", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campanhas : Updates an existing campanha.
     *
     * @param campanha the campanha to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campanha,
     * or with status 400 (Bad Request) if the campanha is not valid,
     * or with status 500 (Internal Server Error) if the campanha couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campanhas")
    public ResponseEntity<Campanha> updateCampanha(@Valid @RequestBody Campanha campanha) throws URISyntaxException {
        log.debug("REST request to update Campanha : {}", campanha);
        if (campanha.getId() == null) {
            return createCampanha(campanha);
        }
        Campanha result = campanhaRepository.save(campanha);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("campanha", campanha.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campanhas : get all the campanhas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of campanhas in body
     */
    @GetMapping("/campanhas")
    public List<Campanha> getAllCampanhas() {
        log.debug("REST request to get all Campanhas");
        List<Campanha> campanhas = campanhaRepository.findAll();
        return campanhas;
    }

    /**
     * GET  /campanhas/:id : get the "id" campanha.
     *
     * @param id the id of the campanha to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campanha, or with status 404 (Not Found)
     */
    @GetMapping("/campanhas/{id}")
    public ResponseEntity<Campanha> getCampanha(@PathVariable Long id) {
        log.debug("REST request to get Campanha : {}", id);
        Campanha campanha = campanhaRepository.findOne(id);
        return Optional.ofNullable(campanha)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /campanhas/:id : delete the "id" campanha.
     *
     * @param id the id of the campanha to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campanhas/{id}")
    public ResponseEntity<Void> deleteCampanha(@PathVariable Long id) {
        log.debug("REST request to delete Campanha : {}", id);
        campanhaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("campanha", id.toString())).build();
    }

}
