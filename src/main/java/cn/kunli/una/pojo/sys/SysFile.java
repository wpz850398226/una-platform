package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysFile extends BasePojo implements Serializable {

    private String originName;

    private Long size;

    private Integer width;

    private Integer height;

    private String path;

    private String typeDcode;

    private String extension;

    private Integer entityId;


//    @TableField(exist = false)
//    private MultipartFile[] fileArray;
    @NotNull(message = "保存失败:文件不能为空")
    @TableField(exist = false)
    private MultipartFile file;

    private static final long serialVersionUID = 1L;


}
