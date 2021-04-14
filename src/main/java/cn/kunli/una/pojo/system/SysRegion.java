package cn.kunli.una.pojo.system;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * (SysRegion)实体类
 *
 * @author Ponzio
 * @since 2020-06-29 15:18:43
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRegion extends BasePojo implements Serializable {
    private static final long serialVersionUID = -94885096018253815L;
    //类型字典id
    private String typeDicId;
    //名称
    private String title;
    //等级
    private Integer level;
    //地区编号
    private String code;
    //面积
    private String area;
    //人口
    private String population;
    //描述
    private String description;
    //根（顶级父id）id
    private String rootId;
    //父字典 id（私有）
    private String parentId;
    //权重，排序用




    @Transient
    private String parentName;
    @Transient
    private String rootName;		//根目录名称
    @Transient
    private String typeDname;     //类型名称
    @Transient
    private List<SysRegion> children;

}
