package cn.kunli.una.pojo.workflow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程定义(WorkflowDefinition)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:03:55
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowDefinition extends BasePojo implements Serializable {
    private static final long serialVersionUID = -89055343881786180L;
    //主键id
    private Integer id;
    //名称
    private String name;
    //实体id
    private Integer entityId;
    //公司id
    private Integer companyId;
    //部门id
    private Integer departmentId;
    //流程定义编码
    private String code;
    //版本号
    private Integer version;
    //备注
    private String remark;
    //创建人id
    private Integer creatorId;
    //创建时间
    private Date createTime;
    //修改人id
    private Integer modifierId;
    //修改时间
    private Date modifyTime;
    //是否删除
    private Integer isDelete;
    //创建人名称
    private String creatorName;
    //创建人IP
    private String creatorHost;
    //修订人名称
    private String modifierName;
    //修订人IP
    private String modifierHost;
    //租户id
    private Integer tentId;

}
