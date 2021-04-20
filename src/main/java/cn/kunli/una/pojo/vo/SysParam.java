package cn.kunli.una.pojo.vo;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年10月9日 上午9:43:58
 * 公共父类，封装通用请求参数
 */
@Data
public class SysParam extends LinkedHashMap<String, Object> {
	//页码
	private Long pageNum;
	//每页数量
	private Long pageSize;
	//全部eq(或个别isNull)
	private Map<String,Object> allEqMap;
	//不等于 <>
	private Map<String,Object> neMap;
	//大于 >
	private Map<String,Object> gtMap;
	//大于等于 >=
	private Map<String,Object> geMap;
	//小于 <
	private Map<String,Object> ltMap;
	//小于等于 <=
	private Map<String,Object> leMap;
	//LIKE '%值%'
	private Map<String,Object> likeMap;
	//NOT LIKE '%值%'
	private Map<String,Object> notLikeMap;
	//LIKE '%值'
	private Map<String,Object> likeLeftMap;
	//LIKE '值%'
	private Map<String,Object> likeRightMap;
	//字段 IS NULL
	private List<String> isNullList;
	//字段 IS NOT NULL
	private List<String> isNotNullList;
	//字段 IN (value.get(0), value.get(1), ...)
	private Map<String,List<Object>> inListMap;
	//字段 NOT IN (value.get(0), value.get(1), ...)
	private Map<String,List<Object>> notInListMap;
	//字段 IN ( sql语句 )
	private Map<String,String> inSqlMap;
	//字段 NOT IN ( sql语句 )
	private Map<String,String> notInSqlMap;
	//GROUP BY 字段, ...
	private List<String> groupByList;
	//排序：ORDER BY 字段, ... ASC
	private List<String> orderByAscList;
	//排序：ORDER BY 字段, ... DESC
	private List<String> orderByDescList;
	//排序：ORDER BY 字段, ...
	private Map<String,Boolean> orderByMap;
	//无视优化规则直接拼接到 sql 的最后
	private String lastStr;
	//拼接 EXISTS ( sql语句 )
	private String existsStr;
	//拼接 NOT EXISTS ( sql语句 )
	private String notExistsStr;

}
