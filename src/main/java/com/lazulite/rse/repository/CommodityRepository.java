package com.lazulite.rse.repository;

import com.lazulite.rse.domain.Commodity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Commodity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

}
