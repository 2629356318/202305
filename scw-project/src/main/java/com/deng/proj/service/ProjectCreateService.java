package com.deng.proj.service;

import com.deng.proj.enume.ProjectStatusEnume;
import com.deng.proj.vo.ProjectRedisStorageVo;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 15:52
 * @Version 1.0
 */
public interface ProjectCreateService {
    /**
     * 初始化项目
     * @param memberId
     * @return
     */
    public String initCreateProject(Integer memberId);

    /**
     * 保存项目信息
     * @param auth    项目状态信息
     * @param project 项目全部信息
     */
    public void saveProjectInfo(ProjectStatusEnume auth, ProjectRedisStorageVo project);
}
