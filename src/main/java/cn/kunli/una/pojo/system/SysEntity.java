package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 实体(SysEntity)实体类
 *
 * @author Ponzio
 * @since 2021-02-24 10:40:36
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysEntity extends BasePojo implements Serializable {
    private static final long serialVersionUID = -76972806667390112L;
    //编码/类名
    private String code;
    //所属平台
    private String platformDcode;
    //主父字段id
    private Integer parentFieldId;
    //url前缀
    private String path;
    //是否虚拟实体
    private Integer isVirtual;
    //可否手动添加数据
    private Integer isAddible;
    //是否可修改
    private Integer isUpdatable;
    //是否允许删除
    private Integer isDeletable;
    //是否允许快速添加
    private Integer isQuickAdd;
    //是否能导入
    private Integer isImport;
    //是否能导出
    private Integer isExport;
    //是否自动加载数据
    private Integer isAutoloading;
    //是否表单默认全屏
    private Integer isFullForm;
    //是否自动展开树结构
    private Integer isTreelayout;
    //是否真实删除
    private Integer isRealDelete;
    //可启动流程定义keys
    private String definitionDcodes;


    @Transient
    private List<SysRelation> relationList;
    @Transient
    private List<SysFilter> filterList;
    @Transient
    private List<SysQuery> queryList;
    @Transient
    private List<SysSort> sortList;
    @Transient
    private String parentFieldName;
    @Transient
    private List<SysButton> buttonList;
    @Transient
    private List<SysPermission> permissionList;
}
