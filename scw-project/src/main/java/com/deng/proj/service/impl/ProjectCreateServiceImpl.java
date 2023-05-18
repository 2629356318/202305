package com.deng.proj.service.impl;

import com.alibaba.fastjson.JSON;
import com.deng.proj.entity.*;
import com.deng.proj.enume.ProjectImageTypeEnume;
import com.deng.proj.enume.ProjectStatusEnume;
import com.deng.proj.repo.*;
import com.deng.proj.resp.AppResponse;
import com.deng.proj.service.ProjectCreateService;
import com.deng.proj.vo.ProjectRedisStorageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 15:53
 * @Version 1.0
 */
@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {
    @Value("${temp.profix}")
    private String profix;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    private ProjectTagRepository projectTagRepository;
    @Autowired
    private ProjectTypeRepository projectTypeRepository;
    @Autowired
    private ReturnRepository returnRepository;

    @Override
    public String initCreateProject(Integer memberId) {
        //项目的token
        String token = UUID.randomUUID().toString().replace("-", "");
        //创建项目的临时对象，因为还没有传递的
        ProjectRedisStorageVo initVo = new ProjectRedisStorageVo();
        //设置memberId
        initVo.setMemberid(memberId);
        //把对象转换成字符串
        String jsonString = JSON.toJSONString(initVo);
        //存储到redis
        redisTemplate.opsForValue().set(profix+token,jsonString);
        return token;
    }

    @Override
    public void saveProjectInfo(ProjectStatusEnume auth, ProjectRedisStorageVo projectRedisStorageVo) {
        //1保存项目
        TProject tProject = new TProject();
        BeanUtils.copyProperties(projectRedisStorageVo,tProject);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tProject.setCreatedate(simpleDateFormat.format(new Date()));
        //设置状态
        tProject.setStatus(Integer.toString(auth.getCode()));
        projectRepository.save(tProject);

        //2保存预览图
        TProjectImages tProjectImages = new TProjectImages(null,tProject.getId(),
                projectRedisStorageVo.getHeaderImage(), ProjectImageTypeEnume.HEAD.getCode());
        projectImagesRepository.save(tProjectImages);

     /*   //3保存详情图
        for(String url: projectRedisStorageVo.getDetailsImage()){
            TProjectImages detail = new TProjectImages(null,tProject.getId(),url,
                    ProjectImageTypeEnume.DETAIL.getCode());
            projectImagesRepository.save(detail);
        }

        //4保存标签
        for(Integer tagId: projectRedisStorageVo.getTagids()){
            TProjectTag tProjectTag = new TProjectTag(null, tProject.getId(), tagId);
            projectTagRepository.save(tProjectTag);
        }

        //5保存分类
        for(Integer typeId:projectRedisStorageVo.getTypeids()){
            TProjectType tProjectType = new TProjectType(null, tProject.getId(), typeId);
            projectTypeRepository.save(tProjectType);
        }*/

        //6保存回报
        for(TReturn tReturn: projectRedisStorageVo.getProjectReturns()){
            tReturn.setProjectid(tProject.getId());
            returnRepository.save(tReturn);
        }

        //7删除缓存
        redisTemplate.delete(profix+projectRedisStorageVo.getProjectToken());
    }
}
