package cn.kunli.una.pojo.vo;

import cn.kunli.una.pojo.system.SysSort;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年10月9日 上午9:43:58
 * 公共父类，封装通用请求参数
 */
@Data
public class SysParam{

	List<SysSort> sortList;

	private Long pageNum;
	private Long pageSize;
	private Map<String,Object> eqMap;
	private Map<String,Object> neMap;
	private Map<String,Object> gtMap;
	private Map<String,Object> geMap;
	private Map<String,Object> ltMap;
	private Map<String,Object> leMap;
	private Map<String,Object> likeMap;
	private Map<String,Object> notLikeMap;
	private Map<String,Object> likeLeftMap;
	private Map<String,Object> likeRightMap;
	private Map<String,Object> isNullMap;
	private Map<String,Object> isNotNullMap;
	private Map<String,Object> inMap;
	private Map<String,Object> notInMap;
	private Map<String,Object> inSqlMap;
	private Map<String,Object> notInSqlMap;
	private Map<String,Object> groupByMap;
	private Map<String,Object> orderByAscMap;
	private Map<String,Object> orderByDescMap;
	private Map<String,Object> orMap;
	private Map<String,Object> lastMap;






}
