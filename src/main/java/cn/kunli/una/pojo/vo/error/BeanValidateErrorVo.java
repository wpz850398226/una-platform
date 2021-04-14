package cn.kunli.una.pojo.vo.error;

import lombok.Data;

/**
 * 校验错误结果
 *
 * @author Blue
 */
@Data
public class BeanValidateErrorVo {
    /**
     * 错误字段
     */
    String errorField;
    /**
     * 错误信息
     */
    String errorMsg;
}
