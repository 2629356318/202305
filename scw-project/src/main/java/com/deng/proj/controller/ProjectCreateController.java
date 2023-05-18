package com.deng.proj.controller;

import com.alibaba.fastjson.JSON;
import com.deng.proj.entity.TReturn;
import com.deng.proj.enume.ProjectStatusEnume;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.ProjectCreateService;
import com.deng.proj.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 16:01
 * @Version 1.0
 */
@Api(tags = "项目基本功能模块")
@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectCreateController {
    @Value("${temp.profix}")
    private String profix;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectCreateService projectCreateService;
    @ApiOperation("项目发起第1步-阅读同意协议")
    @PostMapping("/init")
    public AppResponse<String> init(@RequestBody BaseVo vo) {
        String token = vo.getAccessToken();
        //通过令牌获取用户的编号
        String memberId = stringRedisTemplate.opsForValue().get(token);
        //判断用户是否登陆
        if (memberId==null){
            return AppResponse.fail("占时没有访问权限，请先登录");
        }
        //将项目的令牌保存到redis中
        String projectToken = projectCreateService.initCreateProject(Integer.parseInt(memberId));
        return AppResponse.ok(projectToken);
    }

    @ApiOperation("项目发起第2步-保存项目的基本信息")
    @PostMapping("/saveBaseInfo")
    public AppResponse<String> savebaseInfo(@RequestBody ProjectBaseInfoVo vo) {
        //根据项目令牌，查找redis中的数据
        String json = stringRedisTemplate.opsForValue().get(profix+vo.getProjectToken());
        //将redis中的json数据转换成ProjectRedisStorageVo
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(json,ProjectRedisStorageVo.class);
        //将vo中的数据对应到projectRedisStorageVo
        BeanUtils.copyProperties(vo,projectRedisStorageVo);
        //将projectRedisStorageVo从新写到Redis中
        stringRedisTemplate.opsForValue().set(profix+vo.getProjectToken(),JSON.toJSONString(projectRedisStorageVo));
        return AppResponse.ok("ok");
    }

    @ApiOperation("项目发起第3步-项目保存项目回报信息")
    @PostMapping("/saveReturn")
    public AppResponse<String> saveReturnInfo(@RequestBody List<ProjectReturnVo> projectReturnVos) {
        //从第一个回到信息中获取到项目token
        String projectToken = projectReturnVos.get(0).getProjectToken();
        //根据projectToken，从redis中获取到完整的数据
        String json = stringRedisTemplate.opsForValue().get(profix+projectToken);
        //将redis中的json数据转换成ProjectRedisStorageVo
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(json,ProjectRedisStorageVo.class);
        //循环，将回报视图projectReturnVo转换成回报实体类TReturn
        List<TReturn> returnList = new ArrayList<>();
        for(ProjectReturnVo returnVo:projectReturnVos){
            TReturn tReturn = new TReturn();
            BeanUtils.copyProperties(returnVo,tReturn);
            returnList.add(tReturn);
        }
        //将回报实体类列表写到ProjectRedisStorageVo，在转回到Redis中
        projectRedisStorageVo.setProjectReturns(returnList);
        stringRedisTemplate.opsForValue().set(profix+projectToken,JSON.toJSONString(projectRedisStorageVo));
        return AppResponse.ok("ok");
    }

    @ApiOperation("项目发起第4步-项目保存项目回报信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken", value = "用户token", required = true),
            @ApiImplicitParam(name = "projectToken", value = "项目token", required = true),
            @ApiImplicitParam(name = "ops", value = "操作类型，0草稿，1提交", required = true)
    })
    @PostMapping("/submit")
    public AppResponse<Object> submit(@RequestBody ProjectReVo projectReVo) {
        //1查看用户现在是否有效
        String memberId = stringRedisTemplate.opsForValue().get(projectReVo.getAccessToken());
        if (memberId==null){
            return AppResponse.fail("回话不存在了");
        }
        //读取项目信息
        String json = stringRedisTemplate.opsForValue().get(profix + projectReVo.getProjectToken());
        //将redis中的数据转换成ProjectRedisStorageVo
        ProjectRedisStorageVo projectRedisStorageVo = JSON.parseObject(json, ProjectRedisStorageVo.class);
        //判断状态是否为空
        if(projectReVo.getOps()==null) {
            return AppResponse.fail("状态为空");
        }
        //更新项目状态，保存项目
        if(projectReVo.getOps().equals(ProjectStatusEnume.DRAFT.getCode())){
            projectCreateService.saveProjectInfo(ProjectStatusEnume.DRAFT,projectRedisStorageVo);
        }else {
            projectCreateService.saveProjectInfo(ProjectStatusEnume.SUBMIT_AUTH,projectRedisStorageVo);
        }
        return AppResponse.ok("保存成功过");
    }
}
