package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.AlipayFreezeRequest;
import com.lazulite.rse.service.AlipayFreezeRequestService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.AlipayFreezeRequest}.
 */
@RestController
@RequestMapping("/api")
public class AlipayFreezeRequestResource {

    private final Logger log = LoggerFactory.getLogger(AlipayFreezeRequestResource.class);

    private static final String ENTITY_NAME = "alipayFreezeRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlipayFreezeRequestService alipayFreezeRequestService;

    public AlipayFreezeRequestResource(AlipayFreezeRequestService alipayFreezeRequestService) {
        this.alipayFreezeRequestService = alipayFreezeRequestService;
    }

    /**
     * {@code POST  /alipay-freeze-requests} : Create a new alipayFreezeRequest.
     *
     * @param alipayFreezeRequest the alipayFreezeRequest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alipayFreezeRequest, or with status {@code 400 (Bad Request)} if the alipayFreezeRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alipay-freeze-requests")
    public ResponseEntity<AlipayFreezeRequest> createAlipayFreezeRequest(@RequestBody AlipayFreezeRequest alipayFreezeRequest) throws URISyntaxException {
        log.debug("REST request to save AlipayFreezeRequest : {}", alipayFreezeRequest);
        if (alipayFreezeRequest.getId() != null) {
            throw new BadRequestAlertException("A new alipayFreezeRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlipayFreezeRequest result = alipayFreezeRequestService.save(alipayFreezeRequest);
        return ResponseEntity.created(new URI("/api/alipay-freeze-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alipay-freeze-requests} : Updates an existing alipayFreezeRequest.
     *
     * @param alipayFreezeRequest the alipayFreezeRequest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alipayFreezeRequest,
     * or with status {@code 400 (Bad Request)} if the alipayFreezeRequest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alipayFreezeRequest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alipay-freeze-requests")
    public ResponseEntity<AlipayFreezeRequest> updateAlipayFreezeRequest(@RequestBody AlipayFreezeRequest alipayFreezeRequest) throws URISyntaxException {
        log.debug("REST request to update AlipayFreezeRequest : {}", alipayFreezeRequest);
        if (alipayFreezeRequest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlipayFreezeRequest result = alipayFreezeRequestService.save(alipayFreezeRequest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alipayFreezeRequest.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /alipay-freeze-requests} : get all the alipayFreezeRequests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alipayFreezeRequests in body.
     */
    @GetMapping("/alipay-freeze-requests")
    public ResponseEntity<List<AlipayFreezeRequest>> getAllAlipayFreezeRequests(Pageable pageable) {
        log.debug("REST request to get a page of AlipayFreezeRequests");
        Page<AlipayFreezeRequest> page = alipayFreezeRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alipay-freeze-requests/:id} : get the "id" alipayFreezeRequest.
     *
     * @param id the id of the alipayFreezeRequest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alipayFreezeRequest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alipay-freeze-requests/{id}")
    public ResponseEntity<AlipayFreezeRequest> getAlipayFreezeRequest(@PathVariable Long id) {
        log.debug("REST request to get AlipayFreezeRequest : {}", id);
        Optional<AlipayFreezeRequest> alipayFreezeRequest = alipayFreezeRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alipayFreezeRequest);
    }

    /**
     * {@code DELETE  /alipay-freeze-requests/:id} : delete the "id" alipayFreezeRequest.
     *
     * @param id the id of the alipayFreezeRequest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alipay-freeze-requests/{id}")
    public ResponseEntity<Void> deleteAlipayFreezeRequest(@PathVariable Long id) {
        log.debug("REST request to delete AlipayFreezeRequest : {}", id);
        alipayFreezeRequestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
