package com.deng.proj.controller;

import com.deng.proj.entity.TProject;
import com.deng.proj.entity.TProjectImages;
import com.deng.proj.entity.TReturn;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.ProjectInfoService;
import com.deng.proj.vo.ProjectDetailVo;
import com.deng.proj.vo.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:24
 * @Version 1.0
 */
@Api(tags = "项目基本功能模块")
@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectInfoController {
    @Autowired
    private ProjectInfoService projectInfoService;

    @ApiOperation("获取项目回报列表")
    @GetMapping("/details/returns/{projectId}")
    public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId) {
        List<TReturn> returns = projectInfoService.getProjectReturns(projectId);
        return AppResponse.ok(returns);
    }

    @ApiOperation("获取系统全部项目")
    @GetMapping("/all")
    public AppResponse<List<ProjectVo>> all(){
        //1创建一个集合存储全部项目vo的集合
        List<ProjectVo> projectVoList = new ArrayList<>();
        //2查询数据，所有的项目
        List<TProject> allProjects = projectInfoService.getAllProjects();
        //3遍历项目集合，将项目和该项目的图片合在一起
        for(TProject project:allProjects){
            //获取项目的id
            Integer projectId = project.getId();
            //根据项目id读取对应的配图
            List<TProjectImages> projectImages = projectInfoService.getProjectImages(projectId);
            //创建相应的项目vo对象
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(project,projectVo);
            //遍历项目配图
            for(TProjectImages tProjectImages:projectImages){
                //            判断图片类型
                if(tProjectImages.getImgtype()==0){
                    //设置到projectvo的头图属性
                    projectVo.setHeaderImage(tProjectImages.getImgurl());
                }
            }
            //添加项目到vo的集合中
            projectVoList.add(projectVo);
        }
        return AppResponse.ok(projectVoList);
    }

    @ApiOperation("获取项目信息详情")
    @GetMapping("/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId){
        TProject projectInfo = projectInfoService.getProjectInfo(projectId);
        ProjectDetailVo projectDetailVo = new ProjectDetailVo();
        //复制项目属性值到项目VO
        BeanUtils.copyProperties(projectInfo,projectDetailVo);
        //1、查询项目所有的配图
        List<TProjectImages> projectImagesList = projectInfoService.getProjectImages(projectId);
        //从VO获取项目配图集合
        List<String> detailsImageList = projectDetailVo.getDetailsImage();
        //判断项目配图集合是否为空
        if(detailsImageList==null){
            //初始化项目配图集合
            detailsImageList=new ArrayList<>();
        }
        //遍历项目配图集合
        for (TProjectImages projectImages : projectImagesList) {
            //判断项目配图类型，如果是头图，就设置头图属性到vo
            if(projectImages.getImgtype()==0){
                projectDetailVo.setHeaderImage(projectImages.getImgurl());
            }else {
                //如果是其他的配图，就添加到VO的项目配图集合
                detailsImageList.add(projectImages.getImgurl());
            }
        }
        //把项目配图集合关联到vo
        projectDetailVo.setDetailsImage(detailsImageList);
        //2、获取项目所有的支持回报
        List<TReturn> returnList = projectInfoService.getProjectReturns(projectId);
        //设置项目回报到vo
        projectDetailVo.setProjectReturns(returnList);
        //返回值
        return AppResponse.ok(projectDetailVo);
    }

    @ApiOperation("获取指定id的回报详细信息")
    @GetMapping("/returns/info/{returnId}")
    public AppResponse<TReturn> getTReturn(@PathVariable("returnId") Integer returnId){
        TReturn tReturn = projectInfoService.getRetuenInfo(returnId);
        return AppResponse.ok(tReturn);
    }
}
