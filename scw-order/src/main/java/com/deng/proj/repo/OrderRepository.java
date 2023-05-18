package com.deng.proj.repo;

import com.deng.proj.entity.TOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:17
 * @Version 1.0
 */
public interface OrderRepository extends JpaRepository<TOrder, Integer> {
    @Query("select u from TOrder u where u.memberid =:memberId")
    List<TOrder> findTOrderByberId(@Param("memberId")Integer memberId);
}
