package cn.kunli.una.pojo.vo;


import java.util.ArrayList;
import java.util.List;

//分页的工具类
//1_当前页中的数据List
//2_分页参数

/**
 * 存放分页相关的数据
 *
 */
public class SysPageModel {
	//当前页中的数据
	private List list;// 已经分好页的结果集,该list中只有10条记录
	private Integer size;//本次本页查询的记录条数


	// 基本属性
	private Integer pageNum;// 当前页数,从客户端传递到服务端
	private Integer pageSize;  //每页显示的条数,固定的
	private Integer total;  //总记录条数,数据库查出来的

	private Integer pages;// 总页数,计算出来的
	private Integer startRow;// 每页开始记录的索引，计算出来的 limit ? , ?
	private Integer prePage;// 上一页
	private Integer nextPage;// 下一页



	// 扩展属性
	// 一共每页显示9个页码按钮
	private Integer firstPage;// 开始页码
	private Integer lastPage;// 结束页码

	// 完善属性
	private String url;

	//是否为第一页
	private String isFirstPage = "false";
	//是否为最后一页
	private String isLastPage = "false";
	//是否有前一页
	private String hasPreviousPage = "false";
	//是否有下一页
	private String hasNextPage = "false";
	//导航页码数
	private Integer navigatePages = 9;
	//所有导航页号
	private List<Integer> navigatepageNums = new ArrayList<>();


	// 要想使用我的分页，必须给我两个参数。一个是要看哪一页，另一个是总记录条数
	public SysPageModel(List list, int pageSize, int pageNum, int total) {
		this.pageNum = pageNum;
		this.total = total;
		this.list = list;

		// 计算查询记录的开始索引
		startRow = (pageNum - 1) * pageSize;
		// 计算总页数
		pages = total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
		//sqlserver需要重新计算
		this.pageSize = pageSize;


		firstPage = pageNum - 4;
		lastPage = pageNum + 4;
		// 看看总页数够不够9页
		if (pages > 9) {
			// 超过了9页
			if (firstPage < 1) {
				firstPage = 1;
				lastPage = firstPage + 8;
			}
			if (lastPage > pages) {
				lastPage = pages;
				firstPage = lastPage - 8;
			}
		} else {
			// 不够9页
			firstPage = 1;
			lastPage = pages;
		}

		for(int i = firstPage;i<=lastPage;i++) {
			navigatepageNums.add(i);
		}
	}





	public List<Integer> getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(List<Integer> navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public String getIsFirstPage() {
		return isFirstPage;
	}

	public String getIsLastPage() {
		return isLastPage;
	}

	public String getHasPreviousPage() {
		return hasPreviousPage;
	}

	public String getHasNextPage() {
		return hasNextPage;
	}

	public void setIsFirstPage(String isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setIsLastPage(String isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setHasPreviousPage(String hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public void setHasNextPage(String hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPrePage() {
		prePage = pageNum - 1;
		if (prePage < 1) {
			prePage = 1;
		}
		return prePage;
	}

	public int getNextPage() {
		nextPage = pageNum + 1;
		if (nextPage > pages) {
			nextPage = pages;
		}
		return nextPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getSize() {
		size = list.size();
		return size;
	}


}
