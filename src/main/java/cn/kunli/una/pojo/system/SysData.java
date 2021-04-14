package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据/表单内容/问卷答案(SysData)实体类
 *
 * @author Ponzio
 * @since 2021-04-02 16:14:22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysData extends BasePojo implements Serializable {

    private static final long serialVersionUID = 159534622053945179L;
    //所属实体类id
    @NotNull(message = "实体id不能为空！")
    private Integer entityId;
    //值，扩展字段
    @ColumnType(typeHandler = cn.kunli.una.handler.JsonHandler.class)
    private JSONObject value;
}
