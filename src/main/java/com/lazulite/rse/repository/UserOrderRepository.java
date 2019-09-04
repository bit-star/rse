package com.lazulite.rse.repository;

import com.lazulite.rse.domain.UserOrder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the UserOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("select userOrder from UserOrder userOrder where userOrder.user.login = ?#{principal.username}")
    List<UserOrder> findByUserIsCurrentUser();

}
