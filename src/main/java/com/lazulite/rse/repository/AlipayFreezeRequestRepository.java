package com.lazulite.rse.repository;

import com.lazulite.rse.domain.AlipayFreezeRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AlipayFreezeRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlipayFreezeRequestRepository extends JpaRepository<AlipayFreezeRequest, Long> {

}
