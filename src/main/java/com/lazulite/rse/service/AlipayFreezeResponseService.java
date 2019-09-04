package com.lazulite.rse.service;

import com.lazulite.rse.domain.AlipayFreezeResponse;
import com.lazulite.rse.repository.AlipayFreezeResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AlipayFreezeResponse}.
 */
@Service
@Transactional
public class AlipayFreezeResponseService {

    private final Logger log = LoggerFactory.getLogger(AlipayFreezeResponseService.class);

    private final AlipayFreezeResponseRepository alipayFreezeResponseRepository;

    public AlipayFreezeResponseService(AlipayFreezeResponseRepository alipayFreezeResponseRepository) {
        this.alipayFreezeResponseRepository = alipayFreezeResponseRepository;
    }

    /**
     * Save a alipayFreezeResponse.
     *
     * @param alipayFreezeResponse the entity to save.
     * @return the persisted entity.
     */
    public AlipayFreezeResponse save(AlipayFreezeResponse alipayFreezeResponse) {
        log.debug("Request to save AlipayFreezeResponse : {}", alipayFreezeResponse);
        return alipayFreezeResponseRepository.save(alipayFreezeResponse);
    }

    /**
     * Get all the alipayFreezeResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AlipayFreezeResponse> findAll(Pageable pageable) {
        log.debug("Request to get all AlipayFreezeResponses");
        return alipayFreezeResponseRepository.findAll(pageable);
    }


    /**
     * Get one alipayFreezeResponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AlipayFreezeResponse> findOne(Long id) {
        log.debug("Request to get AlipayFreezeResponse : {}", id);
        return alipayFreezeResponseRepository.findById(id);
    }

    /**
     * Delete the alipayFreezeResponse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AlipayFreezeResponse : {}", id);
        alipayFreezeResponseRepository.deleteById(id);
    }
}
