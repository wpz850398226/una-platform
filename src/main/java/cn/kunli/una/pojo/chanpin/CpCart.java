package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商城-购物车(CpCart)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpCart extends BasePojo implements Serializable {
    private static final long serialVersionUID = 423186745884095629L;
    //商品型号id
    private Integer modelId;
    //数量
    private Integer amount;

    @TableField(exist = false)
    private CpModel cpModel;
}
