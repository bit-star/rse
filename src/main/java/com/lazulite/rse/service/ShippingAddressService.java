package com.lazulite.rse.service;

import com.lazulite.rse.domain.ShippingAddress;
import com.lazulite.rse.repository.ShippingAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ShippingAddress}.
 */
@Service
@Transactional
public class ShippingAddressService {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressService.class);

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    /**
     * Save a shippingAddress.
     *
     * @param shippingAddress the entity to save.
     * @return the persisted entity.
     */
    public ShippingAddress save(ShippingAddress shippingAddress) {
        log.debug("Request to save ShippingAddress : {}", shippingAddress);
        return shippingAddressRepository.save(shippingAddress);
    }

    /**
     * Get all the shippingAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShippingAddress> findAll(Pageable pageable) {
        log.debug("Request to get all ShippingAddresses");
        return shippingAddressRepository.findAll(pageable);
    }


    /**
     * Get one shippingAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShippingAddress> findOne(Long id) {
        log.debug("Request to get ShippingAddress : {}", id);
        return shippingAddressRepository.findById(id);
    }

    /**
     * Delete the shippingAddress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShippingAddress : {}", id);
        shippingAddressRepository.deleteById(id);
    }
}
