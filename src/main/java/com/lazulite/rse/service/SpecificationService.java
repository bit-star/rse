package com.lazulite.rse.service;

import com.lazulite.rse.domain.Specification;
import com.lazulite.rse.repository.SpecificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Specification}.
 */
@Service
@Transactional
public class SpecificationService {

    private final Logger log = LoggerFactory.getLogger(SpecificationService.class);

    private final SpecificationRepository specificationRepository;

    public SpecificationService(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    /**
     * Save a specification.
     *
     * @param specification the entity to save.
     * @return the persisted entity.
     */
    public Specification save(Specification specification) {
        log.debug("Request to save Specification : {}", specification);
        return specificationRepository.save(specification);
    }

    /**
     * Get all the specifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Specification> findAll(Pageable pageable) {
        log.debug("Request to get all Specifications");
        return specificationRepository.findAll(pageable);
    }


    /**
     * Get one specification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Specification> findOne(Long id) {
        log.debug("Request to get Specification : {}", id);
        return specificationRepository.findById(id);
    }

    /**
     * Delete the specification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Specification : {}", id);
        specificationRepository.deleteById(id);
    }
}
