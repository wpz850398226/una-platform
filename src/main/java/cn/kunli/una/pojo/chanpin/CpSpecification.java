package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 商城-规格表(CpSpecification)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpSpecification extends BasePojo implements Serializable {
    private static final long serialVersionUID = 104093829423456677L;
    //类型id
    private String typeDcode;
    //商品id
    private Integer goodsId;
    //规格属性，用逗号分隔
    private String attributeNames;
}
