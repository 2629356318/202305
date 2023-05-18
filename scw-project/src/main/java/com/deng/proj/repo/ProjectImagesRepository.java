package com.deng.proj.repo;

import com.deng.proj.entity.TProjectImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 项目图片持久层
 * @Author by DHF
 * @Date 2021/12/2021/12/23 14:08
 * @Version 1.0
 */
public interface ProjectImagesRepository extends JpaRepository<TProjectImages, Integer> {
    List<TProjectImages> findByProjectid(Integer projectId);
}
