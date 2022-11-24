package cn.kunli.una.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年10月9日 上午9:43:58
 * 公共父类，封装通用请求参数
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BasePojo implements Serializable{
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private String name;					//名称（中文名）

	private String description;

	//权重
	private Integer weight;

	@ApiModelProperty(value = "创建时间", dataType = "Date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@ApiModelProperty(value = "修订时间", dataType = "Date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;			//修订时间

	@ApiModelProperty(value = "创建人id", dataType = "Integer")
	private Integer creatorId;

	@ApiModelProperty(value = "创建人名称", dataType = "String")
	private String creatorName;

	@ApiModelProperty(value = "修订人id", dataType = "Integer")
	private Integer modifierId;

	@ApiModelProperty(value = "修订人名称", dataType = "String")
	private String modifierName;

	@ApiModelProperty(value = "是否删除", dataType = "Boolean")
	private Boolean isDelete;

	@ApiModelProperty(value = "顺序", dataType = "Integer")
	private Integer sortOrder;

	@ApiModelProperty(value = "所属组织id", dataType = "Integer")
	private Integer companyId;

	@ApiModelProperty(value = "所属部门id", dataType = "Integer")
	private Integer departmentId;



	@ApiModelProperty(value = "批量id", dataType = "String")
	@TableField(exist = false)
	private String ids;					//批量操作时，接收批量ids

	@ApiModelProperty(value = "树结构根目录ids", dataType = "String")
	@TableField(exist = false)
	private String rootTreeIds;			//加载带结构树的表格时，如果树结构与表格实体一致，接收树结构根目录ids

	@ApiModelProperty(value = "临时数据", dataType = "String")
	@TableField(exist = false)
	private String tempData;			//临时数据，接收临时数据

	@ApiModelProperty(value = "查询结果的参数key", dataType = "String")
	@TableField(exist = false)
	private String paramName;			//redis或session中存储查询结果的参数名称

	@ApiModelProperty(value = "页码", dataType = "Integer")
	@TableField(exist = false)
	private Integer pageNum;			// 当前页数,从客户端传递到服务端

	@ApiModelProperty(value = "页距", dataType = "Integer")
	@TableField(exist = false)
	private Integer pageSize;  			//每页显示的条数,固定的

	@ApiModelProperty(value = "数量，统计用", dataType = "Integer")
	@TableField(exist = false)
	private Integer count;				//数量，查询统计表用

	@ApiModelProperty(value = "key", dataType = "String")
	@TableField(exist = false)
	private String key;

	@ApiModelProperty(value = "关键字", dataType = "String")
	@TableField(exist = false)
	private String keyword;						//查询关键字

	@ApiModelProperty(value = "高级查询", dataType = "String")
	@TableField(exist = false)
	private String advancedQuery;				//高级筛选语句

	@ApiModelProperty(value = "所属部门id", dataType = "String")
	@TableField(exist = false)
	private String statPattern;					//统计维度

	@ApiModelProperty(value = "参数map", dataType = "Map")
	@TableField(exist = false)
	private Map<String,Object> map;

	@ApiModelProperty(value = "起始时间", dataType = "Date")
	@TableField(exist = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@ApiModelProperty(value = "截止时间", dataType = "Date")
	@TableField(exist = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	@ApiModelProperty(value = "标题", dataType = "String")
	@TableField(exist = false)
	private String title;

	public String getTitle() {
		return name;
	}
}
