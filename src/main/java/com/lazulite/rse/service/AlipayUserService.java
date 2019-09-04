package com.lazulite.rse.service;

import com.lazulite.rse.domain.AlipayUser;
import com.lazulite.rse.repository.AlipayUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AlipayUser}.
 */
@Service
@Transactional
public class AlipayUserService {

    private final Logger log = LoggerFactory.getLogger(AlipayUserService.class);

    private final AlipayUserRepository alipayUserRepository;

    public AlipayUserService(AlipayUserRepository alipayUserRepository) {
        this.alipayUserRepository = alipayUserRepository;
    }

    /**
     * Save a alipayUser.
     *
     * @param alipayUser the entity to save.
     * @return the persisted entity.
     */
    public AlipayUser save(AlipayUser alipayUser) {
        log.debug("Request to save AlipayUser : {}", alipayUser);
        return alipayUserRepository.save(alipayUser);
    }

    /**
     * Get all the alipayUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AlipayUser> findAll(Pageable pageable) {
        log.debug("Request to get all AlipayUsers");
        return alipayUserRepository.findAll(pageable);
    }


    /**
     * Get one alipayUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AlipayUser> findOne(Long id) {
        log.debug("Request to get AlipayUser : {}", id);
        return alipayUserRepository.findById(id);
    }

    /**
     * Delete the alipayUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AlipayUser : {}", id);
        alipayUserRepository.deleteById(id);
    }
}
