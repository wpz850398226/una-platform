package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangpz
 * @since 2023-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_form")
@ApiModel(value="SysForm对象", description="")
public class SysForm extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer entityId;

}
