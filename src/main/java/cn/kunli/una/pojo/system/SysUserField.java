package cn.kunli.una.pojo.system;

import java.io.Serializable;

public class SysUserField implements Serializable {
    private String userId;

    private String fieldId;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }


}
