package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysConfiguration)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:32
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-配置", description="")
public class SysConfiguration extends BasePojo implements Serializable {
    private static final long serialVersionUID = 470869590176267899L;
    //平台id
    private Integer entityId;
    //类型
    private String typeDcode;
    //编码
    private String code;
    //描述
    private String description;
    //值
    private String value;
    //下限值
    private String lower;
    //上限值
    private String upper;

}
