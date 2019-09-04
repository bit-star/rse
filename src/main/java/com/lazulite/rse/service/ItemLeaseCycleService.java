package com.lazulite.rse.service;

import com.lazulite.rse.domain.ItemLeaseCycle;
import com.lazulite.rse.repository.ItemLeaseCycleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ItemLeaseCycle}.
 */
@Service
@Transactional
public class ItemLeaseCycleService {

    private final Logger log = LoggerFactory.getLogger(ItemLeaseCycleService.class);

    private final ItemLeaseCycleRepository itemLeaseCycleRepository;

    public ItemLeaseCycleService(ItemLeaseCycleRepository itemLeaseCycleRepository) {
        this.itemLeaseCycleRepository = itemLeaseCycleRepository;
    }

    /**
     * Save a itemLeaseCycle.
     *
     * @param itemLeaseCycle the entity to save.
     * @return the persisted entity.
     */
    public ItemLeaseCycle save(ItemLeaseCycle itemLeaseCycle) {
        log.debug("Request to save ItemLeaseCycle : {}", itemLeaseCycle);
        return itemLeaseCycleRepository.save(itemLeaseCycle);
    }

    /**
     * Get all the itemLeaseCycles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ItemLeaseCycle> findAll(Pageable pageable) {
        log.debug("Request to get all ItemLeaseCycles");
        return itemLeaseCycleRepository.findAll(pageable);
    }


    /**
     * Get one itemLeaseCycle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ItemLeaseCycle> findOne(Long id) {
        log.debug("Request to get ItemLeaseCycle : {}", id);
        return itemLeaseCycleRepository.findById(id);
    }

    /**
     * Delete the itemLeaseCycle by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemLeaseCycle : {}", id);
        itemLeaseCycleRepository.deleteById(id);
    }
}
