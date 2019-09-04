package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.AlipayFreezeResponse;
import com.lazulite.rse.service.AlipayFreezeResponseService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.AlipayFreezeResponse}.
 */
@RestController
@RequestMapping("/api")
public class AlipayFreezeResponseResource {

    private final Logger log = LoggerFactory.getLogger(AlipayFreezeResponseResource.class);

    private static final String ENTITY_NAME = "alipayFreezeResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlipayFreezeResponseService alipayFreezeResponseService;

    public AlipayFreezeResponseResource(AlipayFreezeResponseService alipayFreezeResponseService) {
        this.alipayFreezeResponseService = alipayFreezeResponseService;
    }

    /**
     * {@code POST  /alipay-freeze-responses} : Create a new alipayFreezeResponse.
     *
     * @param alipayFreezeResponse the alipayFreezeResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alipayFreezeResponse, or with status {@code 400 (Bad Request)} if the alipayFreezeResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alipay-freeze-responses")
    public ResponseEntity<AlipayFreezeResponse> createAlipayFreezeResponse(@RequestBody AlipayFreezeResponse alipayFreezeResponse) throws URISyntaxException {
        log.debug("REST request to save AlipayFreezeResponse : {}", alipayFreezeResponse);
        if (alipayFreezeResponse.getId() != null) {
            throw new BadRequestAlertException("A new alipayFreezeResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlipayFreezeResponse result = alipayFreezeResponseService.save(alipayFreezeResponse);
        return ResponseEntity.created(new URI("/api/alipay-freeze-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alipay-freeze-responses} : Updates an existing alipayFreezeResponse.
     *
     * @param alipayFreezeResponse the alipayFreezeResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alipayFreezeResponse,
     * or with status {@code 400 (Bad Request)} if the alipayFreezeResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alipayFreezeResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alipay-freeze-responses")
    public ResponseEntity<AlipayFreezeResponse> updateAlipayFreezeResponse(@RequestBody AlipayFreezeResponse alipayFreezeResponse) throws URISyntaxException {
        log.debug("REST request to update AlipayFreezeResponse : {}", alipayFreezeResponse);
        if (alipayFreezeResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlipayFreezeResponse result = alipayFreezeResponseService.save(alipayFreezeResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alipayFreezeResponse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /alipay-freeze-responses} : get all the alipayFreezeResponses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alipayFreezeResponses in body.
     */
    @GetMapping("/alipay-freeze-responses")
    public ResponseEntity<List<AlipayFreezeResponse>> getAllAlipayFreezeResponses(Pageable pageable) {
        log.debug("REST request to get a page of AlipayFreezeResponses");
        Page<AlipayFreezeResponse> page = alipayFreezeResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alipay-freeze-responses/:id} : get the "id" alipayFreezeResponse.
     *
     * @param id the id of the alipayFreezeResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alipayFreezeResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alipay-freeze-responses/{id}")
    public ResponseEntity<AlipayFreezeResponse> getAlipayFreezeResponse(@PathVariable Long id) {
        log.debug("REST request to get AlipayFreezeResponse : {}", id);
        Optional<AlipayFreezeResponse> alipayFreezeResponse = alipayFreezeResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alipayFreezeResponse);
    }

    /**
     * {@code DELETE  /alipay-freeze-responses/:id} : delete the "id" alipayFreezeResponse.
     *
     * @param id the id of the alipayFreezeResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alipay-freeze-responses/{id}")
    public ResponseEntity<Void> deleteAlipayFreezeResponse(@PathVariable Long id) {
        log.debug("REST request to delete AlipayFreezeResponse : {}", id);
        alipayFreezeResponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
