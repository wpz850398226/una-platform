package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据权限
 * </p>
 *
 * @author wangpz
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_data_permission")
@ApiModel(value="SysDataPermission对象", description="数据权限")
public class SysDataPermission extends BasePojo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色")
    private Integer roleId;

    @ApiModelProperty(value = "所属实体")
    private Integer entityId;

    @ApiModelProperty(value = "所属字段")
    private Integer fieldId;

    @ApiModelProperty(value = "功能权限类型")
    private String functionTypeDcode;

    @ApiModelProperty(value = "数据权限规则类型")
    private String ruleTypeDcode;

    @ApiModelProperty(value = "阈值")
    private String threshold;

}
