package com.deng.proj.repo;

import com.deng.proj.entity.TMemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<TMemberAddress, Integer> {
    List<TMemberAddress> findByMemberid(Integer memberId);
}
