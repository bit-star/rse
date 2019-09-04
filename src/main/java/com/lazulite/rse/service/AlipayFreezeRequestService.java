package com.lazulite.rse.service;

import com.lazulite.rse.domain.AlipayFreezeRequest;
import com.lazulite.rse.repository.AlipayFreezeRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AlipayFreezeRequest}.
 */
@Service
@Transactional
public class AlipayFreezeRequestService {

    private final Logger log = LoggerFactory.getLogger(AlipayFreezeRequestService.class);

    private final AlipayFreezeRequestRepository alipayFreezeRequestRepository;

    public AlipayFreezeRequestService(AlipayFreezeRequestRepository alipayFreezeRequestRepository) {
        this.alipayFreezeRequestRepository = alipayFreezeRequestRepository;
    }

    /**
     * Save a alipayFreezeRequest.
     *
     * @param alipayFreezeRequest the entity to save.
     * @return the persisted entity.
     */
    public AlipayFreezeRequest save(AlipayFreezeRequest alipayFreezeRequest) {
        log.debug("Request to save AlipayFreezeRequest : {}", alipayFreezeRequest);
        return alipayFreezeRequestRepository.save(alipayFreezeRequest);
    }

    /**
     * Get all the alipayFreezeRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AlipayFreezeRequest> findAll(Pageable pageable) {
        log.debug("Request to get all AlipayFreezeRequests");
        return alipayFreezeRequestRepository.findAll(pageable);
    }


    /**
     * Get one alipayFreezeRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AlipayFreezeRequest> findOne(Long id) {
        log.debug("Request to get AlipayFreezeRequest : {}", id);
        return alipayFreezeRequestRepository.findById(id);
    }

    /**
     * Delete the alipayFreezeRequest by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AlipayFreezeRequest : {}", id);
        alipayFreezeRequestRepository.deleteById(id);
    }
}
