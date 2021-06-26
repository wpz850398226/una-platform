package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 商城-商品参数表(CpGoodsParameter)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpGoodsParameter extends BasePojo implements Serializable {
    private static final long serialVersionUID = 832459622557755918L;
    //所属商品
    private Integer goodsId;
    //参数名
    private String parameterName;
    //参数值
    private String parameterValue;
}
