package cn.kunli.una.pojo.duohui.guanwang;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 配置(GwConfiguration)实体类
 *
 * @author Ponzio
 * @since 2021-03-26 11:26:03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GwConfiguration extends BasePojo implements Serializable {
    private static final long serialVersionUID = -34100705619115428L;
    //所属平台
    private String platformDcode;
    //编码
    private String code;
    //描述
    private String description;
    //值
    private String value;
    //类型
    private String type;
    //模块id/字典目录id
    private String moduleDcode;
    //公司名称
    private String companyName;

}
