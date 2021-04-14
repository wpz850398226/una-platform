package cn.kunli.una.pojo.system;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * (SysException)实体类
 *
 * @author Ponzio
 * @since 2020-07-21 10:38:38
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysException extends BasePojo implements Serializable {
    private static final long serialVersionUID = 208638220392619236L;

    //异常账号
    private String accountUsername;
    //异常密码
    private String accountPassword;
    //错误信息
    private String errorInfo;
    //输入参数
    private String inParam;

}
