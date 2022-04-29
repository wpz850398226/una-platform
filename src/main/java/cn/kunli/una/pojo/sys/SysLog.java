package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 日志(SysLog)实体类
 *
 * @author Ponzio
 * @since 2021-01-15 16:44:45
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysLog extends BasePojo implements Serializable {
    private static final long serialVersionUID = -77063080860752415L;
    //操作相关实体类id
    private Integer entityId;
    //操作相关数据id
    private String dataId;
    //IP地址
    private String ipAddress;
    //操作类型字典id
    private String methodType;
    //来源类型（pc，app）
    private String originalType;
    //描述
    private String packagePath;
    //描述
    private String className;
    //描述
    private String methodName;
    //描述
    private String param;
    //描述
    private String result;

}
