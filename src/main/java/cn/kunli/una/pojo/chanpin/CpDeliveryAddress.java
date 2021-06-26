package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 商城-收件人(CpDeliveryAddress)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:39:59
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpDeliveryAddress extends BasePojo implements Serializable {
    private static final long serialVersionUID = 850518826775358463L;
    //收件人电话
    private String phone;
    //地区id
    private Integer regionId;
    //详细地址
    private String address;
    //是否默认
    private Integer isDefault;
}
