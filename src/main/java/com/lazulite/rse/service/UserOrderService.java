package com.lazulite.rse.service;

import com.lazulite.rse.domain.UserOrder;
import com.lazulite.rse.repository.UserOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserOrder}.
 */
@Service
@Transactional
public class UserOrderService {

    private final Logger log = LoggerFactory.getLogger(UserOrderService.class);

    private final UserOrderRepository userOrderRepository;

    public UserOrderService(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    /**
     * Save a userOrder.
     *
     * @param userOrder the entity to save.
     * @return the persisted entity.
     */
    public UserOrder save(UserOrder userOrder) {
        log.debug("Request to save UserOrder : {}", userOrder);
        return userOrderRepository.save(userOrder);
    }

    /**
     * Get all the userOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UserOrder> findAll(Pageable pageable) {
        log.debug("Request to get all UserOrders");
        return userOrderRepository.findAll(pageable);
    }


    /**
     * Get one userOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserOrder> findOne(Long id) {
        log.debug("Request to get UserOrder : {}", id);
        return userOrderRepository.findById(id);
    }

    /**
     * Delete the userOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserOrder : {}", id);
        userOrderRepository.deleteById(id);
    }
}
