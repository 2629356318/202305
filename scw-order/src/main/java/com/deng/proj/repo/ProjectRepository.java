package com.deng.proj.repo;

import com.deng.proj.entity.TProject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 项目持久层
 * @Author by DHF
 * @Date 2021/12/2021/12/23 14:08
 * @Version 1.0
 */
public interface ProjectRepository extends JpaRepository<TProject, Integer> {
}
