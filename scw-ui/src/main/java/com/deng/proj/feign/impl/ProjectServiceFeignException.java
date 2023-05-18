package com.deng.proj.feign.impl;

import com.deng.proj.feign.ProjectServiceFeign;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 13:50
 * @Version 1.0
 */
@Component
public class ProjectServiceFeignException implements ProjectServiceFeign {
    @Override
    public AppResponse<List<ProjectVo>> all() {
        AppResponse<List<ProjectVo>> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【查询首页热点项目】");
       
        return response;
    }

    @Override
    public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
        AppResponse<ProjectDetailVo> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用失败【查询项目详情】");
        return appResponse;
    }
    @Override
    public AppResponse<ReturnPayConfirmVo> returnInfo(Integer returnId) {
        AppResponse<ReturnPayConfirmVo> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【项目回报】");
        return response;
    }
    @Override
    public AppResponse<String> init(BaseVo vo) {
        AppResponse<String> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【项目同意】");
        return response;
    }

    @Override
    public AppResponse<String> saveBaseInfo(ProjectBaseInfoVo projectBaseInfoVo) {
        AppResponse<String> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【项目创建】");
        return response;
    }

    @Override
    public AppResponse<String> saveReturnInfo(List<ProjectReturnVo> projectReturnVos){
        AppResponse<String> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【回报创建】");
        return response;
    }

    @Override
    public AppResponse<Object> submit(ProjectReVo projectReVo){
        AppResponse<Object> response = AppResponse.fail(null);
        response.setMsg("远程调用失败【项目保存】");
        return response;
}
}
