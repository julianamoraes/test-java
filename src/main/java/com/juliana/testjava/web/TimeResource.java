package com.juliana.testjava.web;

import com.juliana.testjava.domain.Time;

import com.codahale.metrics.annotation.Timed;

import com.juliana.testjava.repository.TimeRepository;
import com.juliana.testjava.web.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

}
