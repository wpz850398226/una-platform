package cn.kunli.una.pojo;

import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.pojo.vo.SysUtilQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;					//名称（中文名）

	private Integer tentId;

	private String remark;

    private Integer creatorId;

    private String creatorHost;

	private String creatorName;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer modifierId;				//修订者id

    private String modifierHost;				//修订者id

	private String modifierName;				//修订者姓名

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;			//修订时间

    private Integer isDelete;			//是否删除：1删除；0未删除

	private Integer sequence;




	@Transient
	private String[] idArray;			//批量操作时，接收批量id数组
	@Transient
	private String ids;					//批量操作时，接收批量ids
	@Transient
	private String rootTreeIds;			//加载带结构树的表格时，如果树结构与表格实体一致，接收树结构更目录ids
	@Transient
	private String tempData;			//临时数据，接收临时数据
	@Transient
	private String paramName;			//redis或session中存储查询结果的参数名称
	@Transient
	private Integer pageNum;			// 当前页数,从客户端传递到服务端
	@Transient
	private Integer pageSize;  			//每页显示的条数,固定的
	@Transient
	private Integer isAll;				//是否全部：0否，1是
	@Transient
	private Integer isResult;			//mybatis查询结果是否是result，1是，0否
	@Transient
	private Integer count;				//数量，查询统计表用
	@Transient
	private String key;
	@Transient
	private String keyword;						//查询关键字
	@Transient
	private String sortsql;						//排序查询语句
	@Transient
	private String sortord;						//排序方式
	@Transient
	private String sortkey;						//排序关键字
	@Transient
	private String standbyCondition;			//备用查询条件
	@Transient
	private String advancedQuery;				//高级筛选语句
	@Transient
	private Integer dataCompanyId;						//所属公司id，通过权限范围查询用
	@Transient
	private Integer dataDepartmentId;					//所属部门id，通过权限范围查询用
	@Transient
	private Integer dataCreatorId;						//所属用户id，通过权限范围查询用
	@Transient
	private String statPattern;					//统计维度
	@Transient
	private List<SysUtilQuery> utilQueryList;						//精确查询字段集合
	@Transient
	private List<SysSort> sortList;				//实体排序规则集合
	@Transient
	private Map<String,Object> map;
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	@Transient
	private String title;

	public String getTitle() {
		return name;
	}
}
