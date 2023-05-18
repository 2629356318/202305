package com.deng.proj.repo;

import com.deng.proj.entity.TMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<TMember, Integer> {
    public TMember findByLoginacct(String loginacct);
}
