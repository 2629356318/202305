package com.deng.proj.repo;

import com.deng.proj.entity.TReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 项目回报持久层
 * @Author by DHF
 * @Date 2021/12/2021/12/23 14:09
 * @Version 1.0
 */
public interface ReturnRepository extends JpaRepository<TReturn, Integer> {
    public List<TReturn> findByProjectid(Integer projectId);
}
