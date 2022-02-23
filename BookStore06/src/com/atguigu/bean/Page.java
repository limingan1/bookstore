package com.atguigu.bean;

import java.util.List;

public class Page<T> {
	private int pageNo;
	private int totalPage;
	private int totalCount;
	//默认每页4条
	private int pageSize=4;
	//索引
	private int index;
	private boolean hasNext;
	private boolean hasPrev;
	private List<T> pageData;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageNo() {
		return pageNo;
	}
	//输入页号，应该是在文本框输入进行快速跳转
	public void setPageNo(int pageNo) {
		//如果页数<=0的话让它为1
		pageNo=pageNo>0?pageNo:1;
		//大于总页数的话让它为总页数
		pageNo=pageNo>getTotalPage()?getTotalPage():pageNo;
		this.pageNo = pageNo;
	}
	//得到总页数
	public int getTotalPage() {
		//总数除以每页条数
		int t=getTotalCount()/getPageSize();
		//如果有余数则总页数加1
		if(!(getTotalCount()%getPageSize()==0)){
			t=t+1;
		}
		return t;
	}
	//设置页号可以不要，页号是用户自己输入的
	public void setTotalPage(int totalPage) {
		
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getIndex() {
		//第一页 0-3
		//第二页4-7
		//索引会随页号变动而变动
		int i=(getPageNo()-1)*getPageSize();
		if(i<0)
			//如果索引<0则让它为0
			i=0;
		return i;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isHasNext() {
		//当前页号是否小于总页数
		return getPageNo()<getTotalPage();
			
	}
	
	public boolean isHasPrev() {
		//当前页号是否大于1
		return getPageNo()>1;
	}
	
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	@Override
	public String toString() {
		return "page [pageNo=" + pageNo + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", index=" + index + ", hasNext=" + hasNext + ", hasPrev="
				+ hasPrev + ", pageData=" + pageData + "]";
	}
	public Page(int pageNo, int totalPage, int totalCount, int pageSize,
			int index, boolean hasNext, boolean hasPrev, List<T> pageData) {
		super();
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.index = index;
		this.hasNext = hasNext;
		this.hasPrev = hasPrev;
		this.pageData = pageData;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
