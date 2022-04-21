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
 * 单词
 * </p>
 *
 * @author wangpz
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("td_word")
@ApiModel(value="TdWord对象", description="单词")
public class TdWord extends BasePojo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "前缀id")
    private Integer prefixOriginId;

    @ApiModelProperty(value = "词根id")
    private Integer rootOriginId;

    @ApiModelProperty(value = "后缀")
    private Integer suffixOriginId;

    @ApiModelProperty(value = "词性")
    private String wordClassDcode;

    @ApiModelProperty(value = "拼词")
    private String spellWord;

    @ApiModelProperty(value = "真词")
    private String realWord;

    @ApiModelProperty(value = "词义")
    private String meaning;


}
