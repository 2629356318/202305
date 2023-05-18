package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.*;

import javax.persistence.*;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:27
 * @Version 1.0
 */
@Entity
@Table(name = "t_project_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "实体类-项目种类关系表")
public class TProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectid;

    private Integer typeid;
}
