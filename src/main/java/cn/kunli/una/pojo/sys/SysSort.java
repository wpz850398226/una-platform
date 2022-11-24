package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="系统-排序", description="")
public class SysSort extends BasePojo implements Serializable {


    private Integer entityId;

    private Integer fieldId;

    private Boolean sortord;


    @TableField(exist = false)
    private Boolean isSortField;        //是否以“排序”字段排序

    private static final long serialVersionUID = 1L;


}
