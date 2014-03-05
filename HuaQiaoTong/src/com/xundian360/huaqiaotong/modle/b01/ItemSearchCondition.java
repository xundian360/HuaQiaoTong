/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b01;

/**
 * 检索商店条件
 * @author  lizhenteng
 * @date      2014年3月5日
 * @version 1.0
 */
public class ItemSearchCondition {
	// 正序
	public static int ORDER_UP = 0;
	// 倒序
	public static int ORDER_DOWN = 1;
	
	// 第一排序条件（价格）
	public static int FIRST_ORDER_PRICE = 0;
	// 第一排序条件（关注）
	public static int FIRST_ORDER_ATTENTION = 1;
	
	// 检索关键字 
	// 饭店：10001 KTV：10002 宾馆：10003 电影院：10004 健身房：10005
	String key;
	// 页码
	int pageSize;
	// 每页显示个数
	int pageNum;
	// 检索关键字
	String searchText; 
	// 检索关键字2
	String searchText2;
	// 最大价格
	Integer maxPrice; 
	// 最小价格
	Integer minPrice; 
	// 价格顺序（0：正序，1：倒序）
	Integer priceOrder;
	// 最大关注
	Integer maxAttention; 
	// 最小关注
	Integer minAttention; 
	// 关注排序（0：正序，1：倒序）
	Integer attentionOrder;
	// 第一排序条件（0：价格，1：关注）
	Integer orderWhich;
	
	public ItemSearchCondition(String key, int pageSize, int pageNum, 
			Integer priceOrder, Integer attentionOrder) {
		super();
		this.key = key;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.priceOrder = priceOrder;
		this.attentionOrder = attentionOrder;
	}
	
	public ItemSearchCondition() {
		super();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getSearchText2() {
		return searchText2;
	}
	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getPriceOrder() {
		return priceOrder;
	}
	public void setPriceOrder(Integer priceOrder) {
		this.priceOrder = priceOrder;
	}
	public Integer getMaxAttention() {
		return maxAttention;
	}
	public void setMaxAttention(Integer maxAttention) {
		this.maxAttention = maxAttention;
	}
	public Integer getMinAttention() {
		return minAttention;
	}
	public void setMinAttention(Integer minAttention) {
		this.minAttention = minAttention;
	}
	public Integer getAttentionOrder() {
		return attentionOrder;
	}
	public void setAttentionOrder(Integer attentionOrder) {
		this.attentionOrder = attentionOrder;
	}
	public Integer getOrderWhich() {
		return orderWhich;
	}
	public void setOrderWhich(Integer orderWhich) {
		this.orderWhich = orderWhich;
	}
	
}
