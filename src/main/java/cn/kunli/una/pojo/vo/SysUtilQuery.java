package cn.kunli.una.pojo.vo;
/*
 * 查询条件类，非数据库表
 *
 */

public class SysUtilQuery {

	// 基本属性

	private String queryName;		//查询单元名称

	private String relation;		//关系

	private String value;			//值




	public String getQueryName() {
		return queryName;
	}




	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}




	public SysUtilQuery() {
		super();
	}




	public String getRelation() {
		return relation;
	}



	public String getValue() {
		return value;
	}



	public void setRelation(String relation) {
		this.relation = relation;
	}



	public void setValue(String value) {
		this.value = value;
	}








}
