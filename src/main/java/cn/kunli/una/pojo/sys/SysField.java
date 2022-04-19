package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 字段(SysField)实体类
 *
 * @author Ponzio
 * @since 2021-02-24 10:39:31
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysField extends BasePojo implements Serializable {
    private static final long serialVersionUID = 233532239948549239L;
    //组名
    private String groupName;
    //赋值字段名称
    private String assignmentCode;
    //取值字段
    private String displayCode;
    //转换取值编码
    private String transformDisplayCode;
    //所属实体id
    private Integer entityId;
    //所属权限id
    private Integer permissionId;
    //所属权限编码
    private String permissionCode;
    //字段注释
    private String annotation;
    //字段类型字典编码
    private String columnTypeDcode;
    //赋值方式
    private String assignmentModeDcode;
    //前端展示类型
    private String displayModeDcode;
    //格式检测类型 复数
    private String formatCheckTypeDcode;
    //数据检测类型 复数
    private String dataCheckTypeDcode;
    //是否必填
    private Boolean isRequired;
    //是否排序展示
    private Boolean isSort;
    //是否可修改；0不可以，1可以
    private Boolean isUpdate;
    //是否导入
    private Boolean isImport;
    //是否导出
    private Boolean isExport;
    //是否批量修改
    private Boolean isBatchUpdate;
    //高级查询单元名称
    private String dqlName;
    //存储长度
    private Integer storageLength;
    //前端展示长度
    private Integer displayLength;
    //可选数量上限（多选下拉，文件选择组件用）
    private Integer selectableLimitNum;
    //默认值
    private String defaultValue;
    //选项实体类id
    private Integer optionEntityId;
    //选项参数名
    private String optionParamName;
    //选项参数值
    private String optionParamValue;
    //选项展示名称字段id
    private String optionNameFieldCode;
    //选项保存值字段id
    private String optionValueFieldCode;
    //选项查询语句
    private String optionSql;
    //单选备选值，逗号隔开
    private String radioOptions;
    //下拉联动父id
    private Integer selectParentId;
    //列固定方式：左，右，无
    private String fixedType;
    //触发隐藏的字段
    private Integer hideFieldId;
    //触发隐藏的选项值
    private String hideFieldValue;
    //是否生效
    private Boolean isEffect;


    //非持久化字段
    @TableField(exist = false)
    private String optionEntityPath;   //选项关联实体地址前缀
    @TableField(exist = false)
    private String selectSubIds;                //联动子id，用,分隔
    @TableField(exist = false)
    private String[] radioOptionArray;          //单选备选值数组
    @TableField(exist = false)
    private String assignmentType;      //赋值类型：手动赋值，自动赋值
    @TableField(exist = false)
    private Map<String,String> hideSubMap;   //联动隐藏子组件，map<组件id，触发值>

}
