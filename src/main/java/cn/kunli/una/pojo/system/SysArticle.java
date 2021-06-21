package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysArticle)实体类
 *
 * @author Ponzio
 * @since 2020-05-06 17:13:09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysArticle extends BasePojo implements Serializable {
    private static final long serialVersionUID = 478525008536411865L;
    //类型
    private String type;
    //内容
    private String content;
    //浏览次数
    private Integer viewAmount;


}
