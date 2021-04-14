package cn.kunli.una.pojo.vo;


import cn.kunli.una.pojo.system.SysEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年5月24日 上午9:44:42
 * 响应参数类，封装通用响应参数
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class SysResponseParameter {

    private SysEntity sysEntity;

    private Integer entityId;			//虚拟实体id，逻辑实体id

    private String entityClass;			//虚拟实体类名，逻辑实体类名

    private Integer operationType = 0;	//操作类型：0新增，1修改

    private Integer batchFlag = 0;		//批量操作标记：0非批量操作，1批量操作

	private String responseType = "1";	//回调函数类型，保存成功后的操作：0为无操作，1为刷新表格，2为刷新页面；默认为1

	private String href;

	private String target;

	private Map<String, Object> params;


}
