package com.deng.proj.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 11:34
 * @Version 1.0
 */
@Entity
@Table(name = "t_project_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "实体类--项目图片")
public class TProjectImages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectid;

    private String imgurl;

    private Integer imgtype;

    private static final long serialVersionUID = 1L;


}
