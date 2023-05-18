package com.deng.proj.enume;

/**
 * @Author by DHF
 * @Date 2021/12/2021/12/23 10:22
 * @Version 1.0
 */
public enum ProjectImageTypeEnume {
    HEAD(0, "预览图"),
    DETAIL(1, "详细图");

    private ProjectImageTypeEnume(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

