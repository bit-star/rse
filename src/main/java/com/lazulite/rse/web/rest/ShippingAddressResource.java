package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.ShippingAddress;
import com.lazulite.rse.service.ShippingAddressService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.ShippingAddress}.
 */
@RestController
@RequestMapping("/api")
public class ShippingAddressResource {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressResource.class);

    private static final String ENTITY_NAME = "shippingAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShippingAddressService shippingAddressService;

    public ShippingAddressResource(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    /**
     * {@code POST  /shipping-addresses} : Create a new shippingAddress.
     *
     * @param shippingAddress the shippingAddress to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shippingAddress, or with status {@code 400 (Bad Request)} if the shippingAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> createShippingAddress(@RequestBody ShippingAddress shippingAddress) throws URISyntaxException {
        log.debug("REST request to save ShippingAddress : {}", shippingAddress);
        if (shippingAddress.getId() != null) {
            throw new BadRequestAlertException("A new shippingAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingAddress result = shippingAddressService.save(shippingAddress);
        return ResponseEntity.created(new URI("/api/shipping-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipping-addresses} : Updates an existing shippingAddress.
     *
     * @param shippingAddress the shippingAddress to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingAddress,
     * or with status {@code 400 (Bad Request)} if the shippingAddress is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shippingAddress couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@RequestBody ShippingAddress shippingAddress) throws URISyntaxException {
        log.debug("REST request to update ShippingAddress : {}", shippingAddress);
        if (shippingAddress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingAddress result = shippingAddressService.save(shippingAddress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shippingAddress.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shipping-addresses} : get all the shippingAddresses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shippingAddresses in body.
     */
    @GetMapping("/shipping-addresses")
    public ResponseEntity<List<ShippingAddress>> getAllShippingAddresses(Pageable pageable) {
        log.debug("REST request to get a page of ShippingAddresses");
        Page<ShippingAddress> page = shippingAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shipping-addresses/:id} : get the "id" shippingAddress.
     *
     * @param id the id of the shippingAddress to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingAddress, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable Long id) {
        log.debug("REST request to get ShippingAddress : {}", id);
        Optional<ShippingAddress> shippingAddress = shippingAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingAddress);
    }

    /**
     * {@code DELETE  /shipping-addresses/:id} : delete the "id" shippingAddress.
     *
     * @param id the id of the shippingAddress to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        log.debug("REST request to delete ShippingAddress : {}", id);
        shippingAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
