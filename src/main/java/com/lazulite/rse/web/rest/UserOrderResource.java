package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.UserOrder;
import com.lazulite.rse.service.UserOrderService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.UserOrder}.
 */
@RestController
@RequestMapping("/api")
public class UserOrderResource {

    private final Logger log = LoggerFactory.getLogger(UserOrderResource.class);

    private static final String ENTITY_NAME = "userOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserOrderService userOrderService;

    public UserOrderResource(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    /**
     * {@code POST  /user-orders} : Create a new userOrder.
     *
     * @param userOrder the userOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userOrder, or with status {@code 400 (Bad Request)} if the userOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-orders")
    public ResponseEntity<UserOrder> createUserOrder(@RequestBody UserOrder userOrder) throws URISyntaxException {
        log.debug("REST request to save UserOrder : {}", userOrder);
        if (userOrder.getId() != null) {
            throw new BadRequestAlertException("A new userOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserOrder result = userOrderService.save(userOrder);
        return ResponseEntity.created(new URI("/api/user-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-orders} : Updates an existing userOrder.
     *
     * @param userOrder the userOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userOrder,
     * or with status {@code 400 (Bad Request)} if the userOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-orders")
    public ResponseEntity<UserOrder> updateUserOrder(@RequestBody UserOrder userOrder) throws URISyntaxException {
        log.debug("REST request to update UserOrder : {}", userOrder);
        if (userOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserOrder result = userOrderService.save(userOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userOrder.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-orders} : get all the userOrders.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userOrders in body.
     */
    @GetMapping("/user-orders")
    public ResponseEntity<List<UserOrder>> getAllUserOrders(Pageable pageable) {
        log.debug("REST request to get a page of UserOrders");
        Page<UserOrder> page = userOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-orders/:id} : get the "id" userOrder.
     *
     * @param id the id of the userOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userOrder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-orders/{id}")
    public ResponseEntity<UserOrder> getUserOrder(@PathVariable Long id) {
        log.debug("REST request to get UserOrder : {}", id);
        Optional<UserOrder> userOrder = userOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userOrder);
    }

    /**
     * {@code DELETE  /user-orders/:id} : delete the "id" userOrder.
     *
     * @param id the id of the userOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-orders/{id}")
    public ResponseEntity<Void> deleteUserOrder(@PathVariable Long id) {
        log.debug("REST request to delete UserOrder : {}", id);
        userOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
