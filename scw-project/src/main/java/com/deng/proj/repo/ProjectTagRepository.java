package com.deng.proj.repo;

import com.deng.proj.entity.TProjectTag;
import com.deng.proj.entity.TProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 项目标签持久层
 * @Author by DHF
 * @Date 2021/12/2021/12/23 14:08
 * @Version 1.0
 */
public interface ProjectTagRepository extends JpaRepository<TProjectTag, Integer> {
}
