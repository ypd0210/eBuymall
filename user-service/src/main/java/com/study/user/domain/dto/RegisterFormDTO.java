package com.study.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: eBuymall
 * @description: 注册用户
 * @author: 心念
 * @create: 2024-09-05 11:58
 **/
@Data
@ApiModel(description = "注册表单实体")
public class RegisterFormDTO {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
