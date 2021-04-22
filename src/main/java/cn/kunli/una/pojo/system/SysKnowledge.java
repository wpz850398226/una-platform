package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysKnowledge)实体类
 *
 * @author Ponzio
 * @since 2020-10-16 17:48:02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysKnowledge extends BasePojo implements Serializable {
    private static final long serialVersionUID = 354289052887519733L;
    //类型（关联字典表）
    private String typeDcode;
    //内容
    private String content;
    //点赞数量
    private Integer praiseAmount;
    //差评数量
    private Integer criticizeAmount;
    //热度，打开次数
    private Object heat;
    //顺序，按从小到大排序，0开始


}
