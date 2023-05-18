package com.deng.proj.mapper;

import java.util.List;
import com.deng.proj.entity.TProject;
import org.apache.ibatis.annotations.Mapper;


/**
 * 项目Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-28
 */
@Mapper
public interface TProjectMapper 
{
    /**
     * 查询项目
     * 
     * @param id 项目主键
     * @return 项目
     */
    public TProject selectTProjectById(Integer id);

    /**
     * 查询项目列表
     * 
     * @param tProject 项目
     * @return 项目集合
     */
    public List<TProject> selectTProjectList(TProject tProject);

    /**
     * 新增项目
     * 
     * @param tProject 项目
     * @return 结果
     */
    public int insertTProject(TProject tProject);

    /**
     * 修改项目
     * 
     * @param tProject 项目
     * @return 结果
     */
    public int updateTProject(TProject tProject);

    /**
     * 删除项目
     * 
     * @param id 项目主键
     * @return 结果
     */
    public int deleteTProjectById(Integer id);

    /**
     * 批量删除项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTProjectByIds(Integer[] ids);
}
