package com.lazulite.rse.repository;

import com.lazulite.rse.domain.ItemLeaseCycle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemLeaseCycle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemLeaseCycleRepository extends JpaRepository<ItemLeaseCycle, Long> {

}
