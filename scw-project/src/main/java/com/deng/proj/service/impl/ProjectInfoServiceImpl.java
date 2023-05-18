package com.deng.proj.service.impl;

import com.deng.proj.entity.TProject;
import com.deng.proj.entity.TProjectImages;
import com.deng.proj.entity.TReturn;
import com.deng.proj.repo.ProjectImagesRepository;
import com.deng.proj.repo.ProjectRepository;
import com.deng.proj.repo.ReturnRepository;
import com.deng.proj.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 19:23
 * @Version 1.0
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {
    @Autowired
    private ReturnRepository returnRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<TReturn> getProjectReturns(Integer projectId) {
        return returnRepository.findByProjectid(projectId);
    }

    @Override
    public List<TProject> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<TProjectImages> getProjectImages(Integer id) {
        return projectImagesRepository.findAll();
    }
    @Override
    public TProject getProjectInfo(Integer projectId) {
        return projectRepository.findById(projectId).get();
    }
    @Override
    public TReturn getRetuenInfo(Integer returnId) {
        return returnRepository.findById(returnId).get();
    }
}
