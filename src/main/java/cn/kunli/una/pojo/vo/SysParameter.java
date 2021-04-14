package cn.kunli.una.pojo.vo;
/**
* @author 作者 : Ponzio
* @version 创建时间：2019年7月4日 上午10:22:43
* 类说明 :通用参数类，封装常用参数，用于查询统计信息、
*/



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysParameter {

	private String name;

	private String userName;

	private String typeDicId;						//类型

	private String[] key;

	private String[] value;

}
