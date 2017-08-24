package com.juliana.testjava.web;

import com.juliana.testjava.domain.Time;

import com.juliana.testjava.repository.TimeRepository;
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
 * REST controller for managing Time.
 */
@RestController
@RequestMapping("/api")
public class TimeResource {

    private final Logger log = LoggerFactory.getLogger(TimeResource.class);
        
    @Inject
    private TimeRepository timeRepository;

    /**
     * POST  /times : Create a new time.
     *
     * @param time the time to create
     * @return the ResponseEntity with status 201 (Created) and with body the new time, or with status 400 (Bad Request) if the time has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/times")
    public ResponseEntity<Time> createTime(@Valid @RequestBody Time time) throws URISyntaxException {
        log.debug("REST request to save Time : {}", time);
        if (time.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("time", "idexists", "A new time cannot already have an ID")).body(null);
        }
        Time result = timeRepository.save(time);
        return ResponseEntity.created(new URI("/api/times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("time", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /times : Updates an existing time.
     *
     * @param time the time to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated time,
     * or with status 400 (Bad Request) if the time is not valid,
     * or with status 500 (Internal Server Error) if the time couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/times")
    public ResponseEntity<Time> updateTime(@Valid @RequestBody Time time) throws URISyntaxException {
        log.debug("REST request to update Time : {}", time);
        if (time.getId() == null) {
            return createTime(time);
        }
        Time result = timeRepository.save(time);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("time", time.getId().toString()))
            .body(result);
    }

    /**
     * GET  /times : get all the times.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of times in body
     */
    @GetMapping("/times")
    public List<Time> getAllTimes() {
        log.debug("REST request to get all Times");
        List<Time> times = timeRepository.findAll();
        return times;
    }

    /**
     * GET  /times/:id : get the "id" time.
     *
     * @param id the id of the time to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the time, or with status 404 (Not Found)
     */
    @GetMapping("/times/{id}")
    public ResponseEntity<Time> getTime(@PathVariable Long id) {
        log.debug("REST request to get Time : {}", id);
        Time time = timeRepository.findOne(id);
        return Optional.ofNullable(time)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /times/:id : delete the "id" time.
     *
     * @param id the id of the time to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        log.debug("REST request to delete Time : {}", id);
        timeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("time", id.toString())).build();
    }

}
