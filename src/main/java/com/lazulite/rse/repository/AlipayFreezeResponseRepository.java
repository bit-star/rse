package com.lazulite.rse.repository;

import com.lazulite.rse.domain.AlipayFreezeResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AlipayFreezeResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlipayFreezeResponseRepository extends JpaRepository<AlipayFreezeResponse, Long> {

}
