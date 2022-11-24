package cn.kunli.una.pojo.td;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 词源
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("td_etymology")
@ApiModel(value="TdEtymology对象", description="词源")
public class TdEtymology extends BasePojo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型",dataType = "String")
    private String typeDcode;

    @ApiModelProperty(value = "来源")
    private String origin;

    @ApiModelProperty(value = "含义")
    private String meaning;

    @ApiModelProperty(value = "词性")
    private String wordClassDcode;

    @ApiModelProperty(value = "位置")
    private String location;
}
