package cn.kunli.una.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * una自定义响应结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysResult<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    /*
     * 状态码见SysHttpCode类
     */
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private T data;

    // 数据总数
    private Long count;

    public static SysResult build(Integer code, String message) {
        return new SysResult(code, message, null,null);
    }

    public static SysResult success() {
        return new SysResult(SysHttpCode.OK,"操作成功",null,null);
    }

    public SysResult success(T data) {
        return new SysResult(SysHttpCode.OK,"操作成功",data,null);
    }

    public SysResult success(T data, Long count) {
        return new SysResult(SysHttpCode.OK,"操作成功",data,count);
    }

    public static SysResult success(String message) {
        return new SysResult(SysHttpCode.OK,message,null,null);
    }

    public SysResult success(String message, T data) {
        return new SysResult(SysHttpCode.OK,message,data,null);
    }

    public static SysResult fail() {
        return new SysResult(SysHttpCode.BAD_REQUEST,"操作失败",null,null);
    }

    public SysResult fail(T data) {
        return new SysResult(SysHttpCode.BAD_REQUEST,"操作失败",data,null);
    }

    public static SysResult fail(String message) {
        return new SysResult(SysHttpCode.BAD_REQUEST,message,null,null);
    }

    public SysResult fail(String message, T data) {
        return new SysResult(SysHttpCode.BAD_REQUEST,message,data,null);
    }

}
