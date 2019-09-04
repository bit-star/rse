package com.lazulite.rse.service;

import com.lazulite.rse.domain.AlipayFundAuthInfo;
import com.lazulite.rse.repository.AlipayFundAuthInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AlipayFundAuthInfo}.
 */
@Service
@Transactional
public class AlipayFundAuthInfoService {

    private final Logger log = LoggerFactory.getLogger(AlipayFundAuthInfoService.class);

    private final AlipayFundAuthInfoRepository alipayFundAuthInfoRepository;

    public AlipayFundAuthInfoService(AlipayFundAuthInfoRepository alipayFundAuthInfoRepository) {
        this.alipayFundAuthInfoRepository = alipayFundAuthInfoRepository;
    }

    /**
     * Save a alipayFundAuthInfo.
     *
     * @param alipayFundAuthInfo the entity to save.
     * @return the persisted entity.
     */
    public AlipayFundAuthInfo save(AlipayFundAuthInfo alipayFundAuthInfo) {
        log.debug("Request to save AlipayFundAuthInfo : {}", alipayFundAuthInfo);
        return alipayFundAuthInfoRepository.save(alipayFundAuthInfo);
    }

    /**
     * Get all the alipayFundAuthInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AlipayFundAuthInfo> findAll(Pageable pageable) {
        log.debug("Request to get all AlipayFundAuthInfos");
        return alipayFundAuthInfoRepository.findAll(pageable);
    }


    /**
     * Get one alipayFundAuthInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AlipayFundAuthInfo> findOne(Long id) {
        log.debug("Request to get AlipayFundAuthInfo : {}", id);
        return alipayFundAuthInfoRepository.findById(id);
    }

    /**
     * Delete the alipayFundAuthInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AlipayFundAuthInfo : {}", id);
        alipayFundAuthInfoRepository.deleteById(id);
    }
}
