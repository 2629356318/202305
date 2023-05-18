package com.deng.proj.repo;

import com.deng.proj.entity.TProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 项目种类持久层
 * @Author by DHF
 * @Date 2021/12/2021/12/23 14:10
 * @Version 1.0
 */
public interface ProjectTypeRepository extends JpaRepository<TProjectType, Integer> {
}
