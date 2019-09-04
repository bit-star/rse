package com.lazulite.rse.service;

import com.lazulite.rse.domain.Commodity;
import com.lazulite.rse.repository.CommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Commodity}.
 */
@Service
@Transactional
public class CommodityService {

    private final Logger log = LoggerFactory.getLogger(CommodityService.class);

    private final CommodityRepository commodityRepository;

    public CommodityService(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    /**
     * Save a commodity.
     *
     * @param commodity the entity to save.
     * @return the persisted entity.
     */
    public Commodity save(Commodity commodity) {
        log.debug("Request to save Commodity : {}", commodity);
        return commodityRepository.save(commodity);
    }

    /**
     * Get all the commodities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Commodity> findAll(Pageable pageable) {
        log.debug("Request to get all Commodities");
        return commodityRepository.findAll(pageable);
    }


    /**
     * Get one commodity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Commodity> findOne(Long id) {
        log.debug("Request to get Commodity : {}", id);
        return commodityRepository.findById(id);
    }

    /**
     * Delete the commodity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Commodity : {}", id);
        commodityRepository.deleteById(id);
    }
}
