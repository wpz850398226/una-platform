package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (SysDepartment)实体类
 *
 * @author Ponzio
 * @since 2020-06-05 10:59:22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysDepartment extends BasePojo implements Serializable {
    private static final long serialVersionUID = 498032112231602074L;
    //部门经理id
    private String departmentManagerId;
    //办公室位置id
    private String officeId;
    //最小内网ip
    private String intranetIpMin;
    //最大内网ip
    private String intranetIpMax;
    //简称
    private String deptNameAlias;
    //经度
    private BigDecimal longitude;
    //纬度
    private BigDecimal latitude;
    //外键，部门级别id
    private String levelDcode;
    //编号，自增列

    //党委委员id
    private String partyCommissionerId;
    //科室联系电话
    private String phone;


    @TableField(exist = false)
    private String departmentManagerName;
    @TableField(exist = false)
    private String officePosition;
    @TableField(exist = false)
    private String companyName;

}
