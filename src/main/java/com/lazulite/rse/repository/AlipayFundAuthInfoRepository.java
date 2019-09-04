package com.lazulite.rse.repository;

import com.lazulite.rse.domain.AlipayFundAuthInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AlipayFundAuthInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlipayFundAuthInfoRepository extends JpaRepository<AlipayFundAuthInfo, Long> {

}
