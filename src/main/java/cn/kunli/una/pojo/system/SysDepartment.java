package cn.kunli.una.pojo.system;

import java.math.BigDecimal;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

/**
 * (SysDepartment)实体类
 *
 * @author Ponzio
 * @since 2020-06-05 10:59:22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysDepartment extends BasePojo implements Serializable {
    private static final long serialVersionUID = 498032112231602074L;
    //所属公司id
    private String companyId;
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


    @Transient
    private String departmentManagerName;
    @Transient
    private String officePosition;
    @Transient
    private String companyName;

}
