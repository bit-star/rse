package com.lazulite.rse.web.rest;

import com.lazulite.rse.domain.AlipayFundAuthInfo;
import com.lazulite.rse.service.AlipayFundAuthInfoService;
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
 * REST controller for managing {@link com.lazulite.rse.domain.AlipayFundAuthInfo}.
 */
@RestController
@RequestMapping("/api")
public class AlipayFundAuthInfoResource {

    private final Logger log = LoggerFactory.getLogger(AlipayFundAuthInfoResource.class);

    private static final String ENTITY_NAME = "alipayFundAuthInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlipayFundAuthInfoService alipayFundAuthInfoService;

    public AlipayFundAuthInfoResource(AlipayFundAuthInfoService alipayFundAuthInfoService) {
        this.alipayFundAuthInfoService = alipayFundAuthInfoService;
    }

    /**
     * {@code POST  /alipay-fund-auth-infos} : Create a new alipayFundAuthInfo.
     *
     * @param alipayFundAuthInfo the alipayFundAuthInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new alipayFundAuthInfo, or with status {@code 400 (Bad Request)} if the alipayFundAuthInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/alipay-fund-auth-infos")
    public ResponseEntity<AlipayFundAuthInfo> createAlipayFundAuthInfo(@RequestBody AlipayFundAuthInfo alipayFundAuthInfo) throws URISyntaxException {
        log.debug("REST request to save AlipayFundAuthInfo : {}", alipayFundAuthInfo);
        if (alipayFundAuthInfo.getId() != null) {
            throw new BadRequestAlertException("A new alipayFundAuthInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlipayFundAuthInfo result = alipayFundAuthInfoService.save(alipayFundAuthInfo);
        return ResponseEntity.created(new URI("/api/alipay-fund-auth-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /alipay-fund-auth-infos} : Updates an existing alipayFundAuthInfo.
     *
     * @param alipayFundAuthInfo the alipayFundAuthInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated alipayFundAuthInfo,
     * or with status {@code 400 (Bad Request)} if the alipayFundAuthInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the alipayFundAuthInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/alipay-fund-auth-infos")
    public ResponseEntity<AlipayFundAuthInfo> updateAlipayFundAuthInfo(@RequestBody AlipayFundAuthInfo alipayFundAuthInfo) throws URISyntaxException {
        log.debug("REST request to update AlipayFundAuthInfo : {}", alipayFundAuthInfo);
        if (alipayFundAuthInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlipayFundAuthInfo result = alipayFundAuthInfoService.save(alipayFundAuthInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, alipayFundAuthInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /alipay-fund-auth-infos} : get all the alipayFundAuthInfos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of alipayFundAuthInfos in body.
     */
    @GetMapping("/alipay-fund-auth-infos")
    public ResponseEntity<List<AlipayFundAuthInfo>> getAllAlipayFundAuthInfos(Pageable pageable) {
        log.debug("REST request to get a page of AlipayFundAuthInfos");
        Page<AlipayFundAuthInfo> page = alipayFundAuthInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /alipay-fund-auth-infos/:id} : get the "id" alipayFundAuthInfo.
     *
     * @param id the id of the alipayFundAuthInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the alipayFundAuthInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/alipay-fund-auth-infos/{id}")
    public ResponseEntity<AlipayFundAuthInfo> getAlipayFundAuthInfo(@PathVariable Long id) {
        log.debug("REST request to get AlipayFundAuthInfo : {}", id);
        Optional<AlipayFundAuthInfo> alipayFundAuthInfo = alipayFundAuthInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(alipayFundAuthInfo);
    }

    /**
     * {@code DELETE  /alipay-fund-auth-infos/:id} : delete the "id" alipayFundAuthInfo.
     *
     * @param id the id of the alipayFundAuthInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/alipay-fund-auth-infos/{id}")
    public ResponseEntity<Void> deleteAlipayFundAuthInfo(@PathVariable Long id) {
        log.debug("REST request to delete AlipayFundAuthInfo : {}", id);
        alipayFundAuthInfoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
