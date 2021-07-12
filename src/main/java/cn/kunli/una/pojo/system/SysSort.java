package cn.kunli.una.pojo.system;

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
public class SysSort extends BasePojo implements Serializable {


    private Integer entityId;

    private Integer fieldId;

    private Boolean sortord;


    @TableField(exist = false)
    private String fieldDqlName;        //排序查询字段语句

    private static final long serialVersionUID = 1L;


}
