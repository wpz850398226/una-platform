package cn.kunli.una.pojo.system;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

/**
 * (SysConfiguration)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:32
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysConfiguration extends BasePojo implements Serializable {
    private static final long serialVersionUID = 470869590176267899L;
    //平台id
    private String platformDcode;
    //类型
    private String type;
    //编码
    private String code;
    //描述
    private String description;
    //值
    private String value;
    //公司id
    private String companyId;
    //模块id
    private String moduleDcode;



    //模块名称
    @Transient
    private String moduleDname;
    //公司名称
    @Transient
    private String companyName;


}
