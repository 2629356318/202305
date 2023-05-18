package com.deng.proj.feign;

import com.deng.proj.config.FeignConfig;
import com.deng.proj.feign.impl.ProjectServiceFeignException;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/24 13:49
 * @Version 1.0
 */
@FeignClient(value = "scw-project",configuration = FeignConfig.class, fallback = ProjectServiceFeignException.class)
public interface ProjectServiceFeign {
    @GetMapping("/project/all")
    public AppResponse<List<ProjectVo>> all();
    @GetMapping("/project/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId);
    @GetMapping("/project/returns/info/{returnId}")
    public AppResponse<ReturnPayConfirmVo> returnInfo(@PathVariable("returnId") Integer returnId);

@PostMapping("/project/init")
public AppResponse<String> init(@RequestBody BaseVo vo);

    @PostMapping("/project/saveBaseInfo")
    public AppResponse<String> saveBaseInfo(@RequestBody ProjectBaseInfoVo projectBaseInfoVo);

    @PostMapping("/project/saveReturn")
    public AppResponse<String> saveReturnInfo(@RequestBody List<ProjectReturnVo> projectReturnVos);

    @PostMapping("/project/submit")
    public AppResponse<Object> submit( @RequestBody ProjectReVo projectReVo);
}
