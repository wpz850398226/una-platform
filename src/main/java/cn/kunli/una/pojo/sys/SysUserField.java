package cn.kunli.una.pojo.sys;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserField implements Serializable {
    private String userId;

    private String fieldId;

    private static final long serialVersionUID = 1L;

}
