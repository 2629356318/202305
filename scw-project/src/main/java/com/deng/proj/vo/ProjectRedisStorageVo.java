package com.deng.proj.vo;

import com.deng.proj.entity.TReturn;
import io.swagger.annotations.ApiModel;
import lombok.Data;


import java.util.List;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 13:38
 * @Version 1.0
 */
@Data
@ApiModel(description = "视图对象---回报列表关系")
public class ProjectRedisStorageVo extends ProjectBaseInfoVo {
    private Integer memberid;//会员id
    private List<TReturn> projectReturns;//项目回报
}
