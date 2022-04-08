package cn.kunli.una.utils.common;

import cn.kunli.una.pojo.vo.SysUtilQuery;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * @author Ponzio
 * @version 2019年7月25日 上午11:05:42
 * 高级查询工具类
 */
public class QueryUtil {


	//前端传回查询list，格式化为查询字符串
	public static String queryListToQueryStr(List<SysUtilQuery> queryList) {

		String queryStr = "";
		if(queryList!=null&&queryList.size()>0) {
			for(SysUtilQuery sysQuery:queryList) {
				//如果查询字段id不是空的且查询值不是空的，则该查询条件有效
				if(StrUtil.isNotBlank(sysQuery.getQueryName())&& StrUtil.isNotBlank(sysQuery.getValue())) {
					queryStr += " and ";
					queryStr = queryStr +sysQuery.getQueryName()+" ";
					queryStr = queryStr + sysQuery.getRelation()+" '";
					if(sysQuery.getRelation().equals("like"))queryStr += "%";
					queryStr = queryStr + sysQuery.getValue();
					if(sysQuery.getRelation().equals("like"))queryStr += "%";
					queryStr = queryStr + "'";
				}
			}
		}
		return queryStr;
	}

	//前端传回排序条件list，格式化为查询排序字符串
	/*public static String sortListToSortSql(List<SysSort> sortList) {

		String sortSql = "";
		if(sortList!=null&&sortList.size()>0) {
			for(int i=0;i<sortList.size();i++) {
				//如果查询字段id不是空的，则该查询条件有效
				if(sortList.get(i).getFieldDqlName()!=null&&!sortList.get(i).getFieldDqlName().equals("")) {
					String fieldDqlName = sortList.get(i).getFieldDqlName();
					if(sortList.get(i).getFieldName().equals("名称")||sortList.get(i).getFieldName().equals("姓名")){
						fieldDqlName = "CONVERT("+fieldDqlName+" USING gbk)";
					}
					if(i!=0)sortSql += ",";
					sortSql += fieldDqlName;
					if(sortList.get(i).getSortord()!=null&&sortList.get(i).getSortord().equals("降序"))sortSql+=" desc";
				}
			}
		}
		return sortSql;
	}*/

	//前端传回排序条件sortkey和sortord，格式化为查询排序字符串
	public static String sortFieldToSortSql(String sortkey,String sortord) {

		String sortSql = "";
		if(StrUtil.isNotBlank(sortkey))sortSql+=sortkey;
		if(StrUtil.isNotBlank(sortord)&&sortord.equals("降序"))sortSql+=" desc";
		return sortSql;
	}

}
