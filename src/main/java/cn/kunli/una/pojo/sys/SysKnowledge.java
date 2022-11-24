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
 * 知识
 * </p>
 *
 * @author wangpz
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_knowledge")
@ApiModel(value="系统-知识", description="知识")
public class SysKnowledge extends BasePojo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型（关联字典表）")
    private String typeDcode;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞数量")
    private Integer praiseAmount;

    @ApiModelProperty(value = "差评数量")
    private Integer criticizeAmount;

    @ApiModelProperty(value = "热度，打开次数")
    private Integer heat;

}
