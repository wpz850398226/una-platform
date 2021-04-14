package cn.kunli.una.pojo.system;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

/**
 * (SysArticle)实体类
 *
 * @author Ponzio
 * @since 2020-05-06 17:13:09
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysArticle extends BasePojo implements Serializable {
    private static final long serialVersionUID = 478525008536411865L;
    //所属平台id（后台，商城，供求平台。。）
    private String type;
    //内容
    private String content;
    //浏览次数
    private Integer viewAmount;


}
