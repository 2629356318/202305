package com.deng.proj.service;

import com.deng.proj.entity.TProject;
import com.deng.proj.entity.TProjectImages;
import com.deng.proj.entity.TReturn;

import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:22
 * @Version 1.0
 */
public interface ProjectInfoService {
    /**
     * 根据项目编号获取项目回报
     */
    public List<TReturn> getProjectReturns(Integer projectId);
    /**
     * 获取当前系统全部项目
     * @return
     */
    public List<TProject> getAllProjects();

    /**
     * 获取指定项目的配图
     * @param id  项目id
     * @return
     */
    public List<TProjectImages> getProjectImages(Integer id);
    /**
     * 获得项目信息
     *
     * @param projectId
     * @return
     */
    public TProject getProjectInfo(Integer projectId);
    /**
     * 获取项目回报详细信息
     * @param returnId
     * @return
     */
    public TReturn getRetuenInfo(Integer returnId);
}
