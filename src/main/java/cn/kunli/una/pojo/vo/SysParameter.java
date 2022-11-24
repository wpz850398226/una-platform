package cn.kunli.una.pojo.vo;
/**
* @author 作者 : Ponzio
* @version 创建时间：2019年7月4日 上午10:22:43
* 类说明 :通用参数类，封装常用参数，用于查询统计信息、
*/


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-参数", description="")
public class SysParameter {

	private String name;

	private String userName;

	private String typeDicId;						//类型

	private String[] key;

	private String[] value;

}
