package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 权限(SysPermission)实体类
 *
 * @author Ponzio
 * @since 2021-02-24 09:38:09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysPermission extends BasePojo implements Serializable {
    private static final long serialVersionUID = 615928081962416975L;
    //类型编码
    private String typeDcode;
    //所属实体id
    private Integer entityId;


    @TableField(exist = false)
    private String menuNames;
    @TableField(exist = false)
    private String code;

}
