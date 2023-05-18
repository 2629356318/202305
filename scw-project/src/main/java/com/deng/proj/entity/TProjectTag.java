package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.*;


/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:26
 * @Version 1.0
 */
@Entity
@Table(name = "t_project_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "实体类-项目标签关系表")
public class TProjectTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectid;

    private Integer tagid;
}
