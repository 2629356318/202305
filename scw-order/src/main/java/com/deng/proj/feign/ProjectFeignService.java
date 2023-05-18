package com.deng.proj.feign;

import com.deng.proj.entity.TReturn;
import com.deng.proj.feign.impl.ProjectFeignServiceException;
import com.deng.proj.resp.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:28
 * @Version 1.0
 */
@FeignClient(value = "scw-project", fallback = ProjectFeignServiceException.class)
public interface ProjectFeignService {
    @GetMapping("/project/details/returns/{projectId}")
    public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId);
}
