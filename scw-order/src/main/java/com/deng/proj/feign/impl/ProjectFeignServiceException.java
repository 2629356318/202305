package com.deng.proj.feign.impl;

import com.deng.proj.entity.TReturn;
import com.deng.proj.feign.ProjectFeignService;
import com.deng.proj.resp.AppResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:30
 * @Version 1.0
 */
@Component
public class ProjectFeignServiceException implements ProjectFeignService {
    @Override
    public AppResponse<List<TReturn>> detailsReturn(Integer projectId) {
        AppResponse<List<TReturn>> fail = AppResponse.fail(null);
        fail.setMsg("远程服务调用失败");
        return fail;
    }
}