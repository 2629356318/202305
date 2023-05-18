package com.deng.proj.service.impl;

import java.util.List;

import com.deng.proj.entity.TProject;
import com.deng.proj.mapper.TProjectMapper;
import com.deng.proj.service.ITProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;

/**
 * 项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-28
 */
@Service
public class TProjectServiceImpl implements ITProjectService
{
    @Autowired
    private TProjectMapper tProjectMapper;

    /**
     * 查询项目
     * 
     * @param id 项目主键
     * @return 项目
     */
    @Override
    public TProject selectTProjectById(Integer id)
    {
        return tProjectMapper.selectTProjectById(id);
    }

    /**
     * 查询项目列表
     * 
     * @param tProject 项目
     * @return 项目
     */
    @Override
    public List<TProject> selectTProjectList(TProject tProject)
    {
        return tProjectMapper.selectTProjectList(tProject);
    }

    /**
     * 新增项目
     * 
     * @param tProject 项目
     * @return 结果
     */
    @Override
    public int insertTProject(TProject tProject)
    {
        return tProjectMapper.insertTProject(tProject);
    }

    /**
     * 修改项目
     * 
     * @param tProject 项目
     * @return 结果
     */
    @Override
    public int updateTProject(TProject tProject)
    {
        return tProjectMapper.updateTProject(tProject);
    }

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteTProjectByIds(Integer[] ids)
    {
        return tProjectMapper.deleteTProjectByIds(ids);
    }

    /**
     * 删除项目信息
     * 
     * @param id 项目主键
     * @return 结果
     */
    @Override
    public int deleteTProjectById(Integer id)
    {
        return tProjectMapper.deleteTProjectById(id);
    }
}
