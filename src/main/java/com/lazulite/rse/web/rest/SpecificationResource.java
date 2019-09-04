package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.Specification;
import com.lazulite.rse.service.SpecificationService;
import com.lazulite.rse.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lazulite.rse.domain.Specification}.
 */
@RestController
@RequestMapping("/api")
public class SpecificationResource {

    private final Logger log = LoggerFactory.getLogger(SpecificationResource.class);

    private static final String ENTITY_NAME = "specification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecificationService specificationService;

    public SpecificationResource(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    /**
     * {@code POST  /specifications} : Create a new specification.
     *
     * @param specification the specification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specification, or with status {@code 400 (Bad Request)} if the specification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specifications")
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) throws URISyntaxException {
        log.debug("REST request to save Specification : {}", specification);
        if (specification.getId() != null) {
            throw new BadRequestAlertException("A new specification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Specification result = specificationService.save(specification);
        return ResponseEntity.created(new URI("/api/specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specifications} : Updates an existing specification.
     *
     * @param specification the specification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specification,
     * or with status {@code 400 (Bad Request)} if the specification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specifications")
    public ResponseEntity<Specification> updateSpecification(@RequestBody Specification specification) throws URISyntaxException {
        log.debug("REST request to update Specification : {}", specification);
        if (specification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Specification result = specificationService.save(specification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specifications} : get all the specifications.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specifications in body.
     */
    @GetMapping("/specifications")
    public ResponseEntity<List<Specification>> getAllSpecifications(Pageable pageable) {
        log.debug("REST request to get a page of Specifications");
        Page<Specification> page = specificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specifications/:id} : get the "id" specification.
     *
     * @param id the id of the specification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specifications/{id}")
    public ResponseEntity<Specification> getSpecification(@PathVariable Long id) {
        log.debug("REST request to get Specification : {}", id);
        Optional<Specification> specification = specificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specification);
    }

    /**
     * {@code DELETE  /specifications/:id} : delete the "id" specification.
     *
     * @param id the id of the specification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specifications/{id}")
    public ResponseEntity<Void> deleteSpecification(@PathVariable Long id) {
        log.debug("REST request to delete Specification : {}", id);
        specificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
