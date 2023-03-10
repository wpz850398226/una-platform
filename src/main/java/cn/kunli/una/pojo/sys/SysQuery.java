package cn.kunli.una.pojo.sys;


import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysQuery extends BasePojo implements Serializable {

    private String assignmentModeDcode;

    private Integer entityId;

    private String fieldId;

    private String optionEntityId;

    private String optionNameFieldCode;            //选项展示名称关联字段id（默认是title的值）

    private String optionValueFieldCode;            //选项值关联字段id（默认是id的值）

    private String optionParamName;

    private String optionParamValue;

    private String defaultValue;

    private String querySql;

    private String selectParentId;            //下拉联动父id

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String fieldCode;
    @TableField(exist = false)
    private String optionEntityPath;
    @TableField(exist = false)
    private String selectSubIds;                //联动子id，用,分隔
    @TableField(exist = false)
    private String assignmentModeDname;
//    @TableField(exist = false)
//    private String optionNameFieldCode;        //选项展示名称字段取值编码
//    @TableField(exist = false)
//    private String optionValueFieldCode;        //选项取值字段取值编码


}
