package cn.kunli.una.pojo.system;


import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysDictionary extends BasePojo implements Serializable {

    private String code;

    private String style;

    private String description;

    private String value;              //字典值

    private String type;            //类型

    private Integer parentId;

    private Integer rootId;			//根id，仅在查询树结构时作为根id使用

    private String queryCondition;       //条件，查询条件

    private String introduction;    //介绍


    @Transient
    private String parentCode;      //父目录编码
    @Transient
    private String brotherName;     //兄弟目录名称
    @Transient
    private String subName;         //子目录名称
    @Transient
    private String parentName;      //父目录名称
    @Transient
    private String grandName;       //爷目录名称
    @Transient
    private String rootName;		//根目录名称
    @Transient
    private List<SysDictionary> children;		//子目录集合
    @Transient
    private List<Map<String,Object>> mapList;
    @Transient
    private Map<String,Object> assignMap;
    @Transient
    private Integer isFinish;   //是否完成节点（工作流字典专用）
    @Transient
    private Object approveResult;   //审批结果（工作流字典专用）
    @Transient
    private Object approveOpinion;   //审批意见（工作流字典专用）

    private static final long serialVersionUID = 1L;


}
