package com.deng.proj.repo;

import com.deng.proj.entity.TTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:17
 * @Version 1.0
 */
public interface TransactionRepository extends JpaRepository<TTransaction, Integer> {
}
