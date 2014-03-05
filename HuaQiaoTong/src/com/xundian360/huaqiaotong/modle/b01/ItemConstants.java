/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b01;

import com.xundian360.huaqiaotong.R;

/**
 * 
 * 显示项目常量
 * 
 * @author Administrator
 * @date 2013年12月4日
 * @version 1.0
 */
public class ItemConstants {

	public static final int ITEM_NAV_NULL = -1;

	// 按照价格搜索区分
	public static final int ITEM_SERACH_BY_PRICE = 1;
	// 按照关注搜索区分
	public static final int ITEM_SERACH_BY_ATTENTION = 2;
	// 按照关键字搜索区分
	public static final int ITEM_SERACH_BY_KEY = 3;
	
	// 默认每页显示个数
	public static final int DEL_PAGE_SIZT = 10;

	/** 饭店标题ID **/
	public static final int RESTAURANT_TEXT_ID = R.string.b01v01_1_tittle_text;
	/** 饭店Key ID **/
	public static final int RESTAURANT_KEY_ID = R.string.b01v01_1_tittle_key;
	/** 饭店筛选项目 **/
	public static final int RESTAURANT_NAV_ID = R.array.b01v01_1_nav;

	/** KTV标题ID **/
	public static final int KTV_TEXT_ID = R.string.b01v01_2_tittle_text;
	/** KTV Key ID **/
	public static final int KTV_KEY_ID = R.string.b01v01_2_tittle_key;
	/** KTV筛选项目 **/
	public static final int KTV_NAV_ID = R.array.b01v01_2_nav;

	/** 宾馆标题ID **/
	public static final int HOTEL_TEXT_ID = R.string.b01v01_3_tittle_text;
	/** 宾馆Key ID **/
	public static final int HOTEL_KEY_ID = R.string.b01v01_3_tittle_key;
	/** 宾馆筛选项目 **/
	public static final int HOTEL_NAV_ID = R.array.b01v01_3_nav;

	/** 健身房标题ID **/
	public static final int JIAN_TEXT_ID = R.string.b01v01_4_tittle_text;
	/** 健身房Key ID **/
	public static final int JIAN_KEY_ID = R.string.b01v01_4_tittle_key;

	/** 电影院标题ID **/
	public static final int MOVE_TEXT_ID = R.string.b01v01_5_tittle_text;
	/** 电影院Key ID **/
	public static final int MOVE_KEY_ID = R.string.b01v01_5_tittle_key;

	/** 饭店筛选项目条件IDs **/
	public static final int[] RESTAURANT_NAV_ITEM_TEXT_IDS = {
			R.array.b01v01_1_nav_1_text, R.array.b01v01_1_nav_2_text,
			R.array.b01v01_1_nav_3_text };
	public static final int[] RESTAURANT_NAV_ITEM_KEY_IDS = {
			R.array.b01v01_1_nav_1_key, R.array.b01v01_1_nav_2_key,
			R.array.b01v01_1_nav_3_key };
	public static final int[] RESTAURANT_NAV_ITEM_SEARCH_TYPE = {
			ITEM_SERACH_BY_KEY, ITEM_SERACH_BY_PRICE, ITEM_SERACH_BY_ATTENTION };

	/** KTV筛选项目条件IDs **/
	public static final int[] KTV_NAV_ITEM_TEXT_IDS = {
			R.array.b01v01_2_nav_1_text, R.array.b01v01_2_nav_2_text };

	public static final int[] KTV_NAV_ITEM_KEY_IDS = {
			R.array.b01v01_2_nav_1_key, R.array.b01v01_2_nav_2_key };
	public static final int[] KTV_NAV_ITEM_SEARCH_TYPE = {
			ITEM_SERACH_BY_PRICE, ITEM_SERACH_BY_ATTENTION };

	/** 宾馆筛选项目条件IDs **/
	public static final int[] HOTEL_NAV_TEXT_IDS = {
			R.array.b01v01_3_nav_1_text, R.array.b01v01_3_nav_2_text };
	public static final int[] HOTEL_NAV_KEY_IDS = { R.array.b01v01_3_nav_1_key,
			R.array.b01v01_3_nav_2_key };
	public static final int[] HOTEL_NAV_ITEM_SEARCH_TYPE = {
			ITEM_SERACH_BY_PRICE, ITEM_SERACH_BY_ATTENTION };
	
	// 导航可展开
	public static final int[] ITEM_CAN_EXPANSION_KEY = {R.array.b01v01_1_nav_1_key};
}
