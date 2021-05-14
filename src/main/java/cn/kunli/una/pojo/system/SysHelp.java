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
public class SysHelp extends BasePojo implements Serializable {

    private Integer entityId;

    private String pageUrl;

    private String name;

    private Integer viewAmount;

    private String content;


    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
    private String entityName;


}
