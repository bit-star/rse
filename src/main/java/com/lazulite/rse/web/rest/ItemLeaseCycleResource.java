package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.ItemLeaseCycle;
import com.lazulite.rse.service.ItemLeaseCycleService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.ItemLeaseCycle}.
 */
@RestController
@RequestMapping("/api")
public class ItemLeaseCycleResource {

    private final Logger log = LoggerFactory.getLogger(ItemLeaseCycleResource.class);

    private static final String ENTITY_NAME = "itemLeaseCycle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemLeaseCycleService itemLeaseCycleService;

    public ItemLeaseCycleResource(ItemLeaseCycleService itemLeaseCycleService) {
        this.itemLeaseCycleService = itemLeaseCycleService;
    }

    /**
     * {@code POST  /item-lease-cycles} : Create a new itemLeaseCycle.
     *
     * @param itemLeaseCycle the itemLeaseCycle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemLeaseCycle, or with status {@code 400 (Bad Request)} if the itemLeaseCycle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-lease-cycles")
    public ResponseEntity<ItemLeaseCycle> createItemLeaseCycle(@RequestBody ItemLeaseCycle itemLeaseCycle) throws URISyntaxException {
        log.debug("REST request to save ItemLeaseCycle : {}", itemLeaseCycle);
        if (itemLeaseCycle.getId() != null) {
            throw new BadRequestAlertException("A new itemLeaseCycle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemLeaseCycle result = itemLeaseCycleService.save(itemLeaseCycle);
        return ResponseEntity.created(new URI("/api/item-lease-cycles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-lease-cycles} : Updates an existing itemLeaseCycle.
     *
     * @param itemLeaseCycle the itemLeaseCycle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemLeaseCycle,
     * or with status {@code 400 (Bad Request)} if the itemLeaseCycle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemLeaseCycle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-lease-cycles")
    public ResponseEntity<ItemLeaseCycle> updateItemLeaseCycle(@RequestBody ItemLeaseCycle itemLeaseCycle) throws URISyntaxException {
        log.debug("REST request to update ItemLeaseCycle : {}", itemLeaseCycle);
        if (itemLeaseCycle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemLeaseCycle result = itemLeaseCycleService.save(itemLeaseCycle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemLeaseCycle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-lease-cycles} : get all the itemLeaseCycles.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemLeaseCycles in body.
     */
    @GetMapping("/item-lease-cycles")
    public ResponseEntity<List<ItemLeaseCycle>> getAllItemLeaseCycles(Pageable pageable) {
        log.debug("REST request to get a page of ItemLeaseCycles");
        Page<ItemLeaseCycle> page = itemLeaseCycleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /item-lease-cycles/:id} : get the "id" itemLeaseCycle.
     *
     * @param id the id of the itemLeaseCycle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemLeaseCycle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-lease-cycles/{id}")
    public ResponseEntity<ItemLeaseCycle> getItemLeaseCycle(@PathVariable Long id) {
        log.debug("REST request to get ItemLeaseCycle : {}", id);
        Optional<ItemLeaseCycle> itemLeaseCycle = itemLeaseCycleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemLeaseCycle);
    }

    /**
     * {@code DELETE  /item-lease-cycles/:id} : delete the "id" itemLeaseCycle.
     *
     * @param id the id of the itemLeaseCycle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-lease-cycles/{id}")
    public ResponseEntity<Void> deleteItemLeaseCycle(@PathVariable Long id) {
        log.debug("REST request to delete ItemLeaseCycle : {}", id);
        itemLeaseCycleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
