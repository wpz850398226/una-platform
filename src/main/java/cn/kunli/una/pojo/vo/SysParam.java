package cn.kunli.una.pojo.vo;

import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年10月9日 上午9:43:58
 * 公共父类，封装通用请求参数
 */
@Data
public class SysParam {
	//页码
	private Long pageNum = 1L;
	//每页数量
	private Long pageSize = 10L;
	//批量id
	private String ids;
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
	private Map<String,Object> likeMap = new HashMap<>();
	//NOT LIKE '%值%'
	private Map<String,Object> notLikeMap;
	//LIKE '%值'
	private Map<String,Object> likeLeftMap;
	//LIKE '值%'
	private Map<String,Object> likeRightMap;
	//字段 IS NULL
	private String[] isNullArray;
	//字段 IS NOT NULL
	private String[] isNotNullArray;
	//字段 IN (value.get(0), value.get(1), ...)
	private Map<String,List<Object>> inListMap;
	//字段 NOT IN (value.get(0), value.get(1), ...)
	private Map<String,List<Object>> notInListMap;
	//字段 IN ( sql语句 )
	private Map<String,String> inSqlMap;
	//字段 NOT IN ( sql语句 )
	private Map<String,String> notInSqlMap;
	//GROUP BY 字段, ...
	private String[] groupByArray;
	//排序：ORDER BY 字段, ... ASC
	private String[] orderByAscArray;
	//排序：ORDER BY 字段, ... DESC
	private String[] orderByDescArray;
	//排序：ORDER BY 字段, ...
	private Map<String,Boolean> orderByMap;
	//无视优化规则直接拼接到 sql 的最后
	private String lastStr;
	//拼接 EXISTS ( sql语句 )
	private String existsStr;
	//拼接 NOT EXISTS ( sql语句 )
	private String notExistsStr;

	public SysParam(Map<String, Object> map) {
		if(MapUtils.isEmpty(map))return;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if(key.contains(":")){
				int index = key.indexOf(":");
				String substring = key.substring(0, index);
				switch(substring){
					case "allEq":
						if(MapUtils.isEmpty(this.getAllEqMap())){
							this.allEqMap = new HashMap<>();
						}
						this.allEqMap.put(key.substring(index+1),entry.getValue());
						break;
					case "ne":
						if(MapUtils.isEmpty(this.neMap)){
							this.neMap = new HashMap<>();
						}
						this.neMap.put(key.substring(index+1),entry.getValue());
						break;
					case "gt":
						if(MapUtils.isEmpty(this.gtMap)){
							this.gtMap = new HashMap<>();
						}
						this.gtMap.put(key.substring(index+1),entry.getValue());
						break;
					case "ge":
						if(MapUtils.isEmpty(this.geMap)){
							this.geMap = new HashMap<>();
						}
						this.geMap.put(key.substring(index+1),entry.getValue());
						break;
					case "lt":
						if(MapUtils.isEmpty(this.ltMap)){
							this.ltMap = new HashMap<>();
						}
						this.ltMap.put(key.substring(index+1),entry.getValue());
						break;
					case "le":
						if(MapUtils.isEmpty(this.leMap)){
							this.leMap = new HashMap<>();
						}
						this.leMap.put(key.substring(index+1),entry.getValue());
						break;
					case "notLike":
						if(MapUtils.isEmpty(this.notLikeMap)){
							this.notLikeMap = new HashMap<>();
						}
						this.notLikeMap.put(key.substring(index+1),entry.getValue());
						break;
					case "likeLeft":
						if(MapUtils.isEmpty(this.likeLeftMap)){
							this.likeLeftMap = new HashMap<>();
						}
						this.likeLeftMap.put(key.substring(index+1),entry.getValue());
						break;
					case "likeRight":
						if(MapUtils.isEmpty(this.likeRightMap)){
							this.likeRightMap = new HashMap<>();
						}
						this.likeRightMap.put(key.substring(index+1),entry.getValue());
						break;
					case "inSql":
						if(MapUtils.isEmpty(this.inSqlMap)){
							this.inSqlMap = new HashMap<>();
						}
						this.inSqlMap.put(key.substring(index+1),entry.getValue().toString());
						break;
					case "notInSql":
						if(MapUtils.isEmpty(this.notInSqlMap)){
							this.notInSqlMap = new HashMap<>();
						}
						this.notInSqlMap.put(key.substring(index+1),entry.getValue().toString());
						break;
				}
			}else{
				switch(key){
					case "pageNum":
						this.pageNum = Long.valueOf(value.toString());
						break;
					case "pageSize":
						this.pageSize = Long.valueOf(value.toString());
						break;
					case "ids":
						if(StringUtils.isBlank(this.ids)){
							this.ids = value.toString();
						}else{
							this.ids = this.ids + "," + value.toString();
						}
						break;
					case "isNull":
						if(ArrayUtils.isEmpty(this.isNullArray)){
							this.isNullArray = value.toString().split(",");
						}else{
							ArrayUtils.addAll(this.isNullArray,value.toString().split(","));
						}
						break;
					case "isNotNull":
						if(ArrayUtils.isEmpty(this.isNotNullArray)){
							this.isNotNullArray = value.toString().split(",");
						}else{
							ArrayUtils.addAll(this.isNotNullArray,value.toString().split(","));
						}
						break;
					case "groupBy":
						if(ArrayUtils.isEmpty(this.groupByArray)){
							this.groupByArray = value.toString().split(",");
						}else{
							ArrayUtils.addAll(this.groupByArray,value.toString().split(","));
						}
						break;
					case "orderByAsc":
						if(ArrayUtils.isEmpty(this.orderByAscArray)){
							this.orderByAscArray = value.toString().split(",");
						}else{
							ArrayUtils.addAll(this.orderByAscArray,value.toString().split(","));
						}
						break;
					case "orderByDesc":
						if(ArrayUtils.isEmpty(this.orderByDescArray)){
							this.orderByDescArray = value.toString().split(",");
						}else{
							ArrayUtils.addAll(this.orderByDescArray,value.toString().split(","));
						}
						break;
					default:
						this.likeMap.put(key,entry.getValue());
						break;
				}
			}
		}
	}
}
