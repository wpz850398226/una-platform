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

	private Integer weight;		//权重

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@ApiModelProperty(value = "修订时间")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;			//修订时间

	@ApiModelProperty(value = "创建人id")
	private Integer creatorId;

	@ApiModelProperty(value = "创建人名称")
	private String creatorName;

	@ApiModelProperty(value = "修订人id")
	private Integer modifierId;

	@ApiModelProperty(value = "修订人名称")
	private String modifierName;

	@ApiModelProperty(value = "是否删除")
	private Boolean isDelete;

	@ApiModelProperty(value = "顺序")
	private Integer sortOrder;

	@ApiModelProperty(value = "所属组织id")
	private Integer companyId;

	@ApiModelProperty(value = "所属部门id")
	private Integer departmentId;




	@TableField(exist = false)
	private String ids;					//批量操作时，接收批量ids
	@TableField(exist = false)
	private String rootTreeIds;			//加载带结构树的表格时，如果树结构与表格实体一致，接收树结构根目录ids
//	@TableField(exist = false)
//	private String tempData;			//临时数据，接收临时数据
	@TableField(exist = false)
	private String paramName;			//redis或session中存储查询结果的参数名称
	@TableField(exist = false)
	private Integer pageNum = 1;			// 当前页数,从客户端传递到服务端
	@TableField(exist = false)
	private Integer pageSize = 10;  			//每页显示的条数,固定的
	@TableField(exist = false)
	private Integer count;				//数量，查询统计表用
	@TableField(exist = false)
	private String key;
	@TableField(exist = false)
	private String keyword;						//查询关键字
	@TableField(exist = false)
	private String advancedQuery;				//高级筛选语句
	@TableField(exist = false)
	private String statPattern;					//统计维度
	@TableField(exist = false)
	private Map<String,Object> map;
	@TableField(exist = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	@TableField(exist = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	@TableField(exist = false)
	private String title;

	public String getTitle() {
		return name;
	}
}
