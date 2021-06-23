package cn.kunli.una.pojo.chanpin;

import java.util.Date;
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

    private Integer id;
    //类型id
    private String typeDcode;
    //商品id
    private Integer goodsId;
    //规格属性，用逗号分隔
    private String attributeNames;
    //名称
    private String name;
    //备注
    private String remark;
    //创建人id
    private Integer creatorId;
    //创建人名称
    private String creatorName;
    //创建人IP
    private String creatorHost;
    //创建时间
    private Date createTime;
    //修改人
    private Integer modifierId;
    //修订人名称
    private String modifierName;
    //修订人IP
    private String modifierHost;
    //修订时间
    private Date modifyTime;
    //租户id
    private Integer tentId;
    //是否逻辑删除
    private Integer isDelete;
    //顺序
    private Integer sortOrder;
    //所属组织id
    private Integer companyId;
    //所属部门id
    private Integer departmentId;

}
