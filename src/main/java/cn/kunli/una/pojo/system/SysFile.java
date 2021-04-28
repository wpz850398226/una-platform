package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
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


    @TableField(exist = false)
    private MultipartFile[] fileArray;
    @TableField(exist = false)
    private MultipartFile file;
    @TableField(exist = false)
    private String typeDname;

    private static final long serialVersionUID = 1L;


}
