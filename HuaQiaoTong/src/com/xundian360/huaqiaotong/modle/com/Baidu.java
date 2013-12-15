/**
 * 
 */
package com.xundian360.huaqiaotong.modle.com;

import java.io.Serializable;

import com.baidu.mapapi.search.MKPoiInfo;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.CommonUtil.GaussSphere;

/**
 * KTV对象
 * @description
 * 百度数据
 *{
 * 	status : 0,
 * 	message : "ok",
 * 	total : 1602,
 * 	results : 
 * 	[
 * 		{
 * 			name : "钱柜(惠新旗舰店)",
 * 			location : 
 * 			{
 * 				lat : 39.993388,
 * 				lng : 116.430796
 * 			},
 * 			address : "惠新东街乙4号",
 * 			telephone : "(010)59639666",
 * 			uid : "87248d96c43561aa2806ce53",
 * 			detail_info : 
 * 			{
 * 				type : "life",
 * 				tag : "KTV,对外经贸",
 * 				detail_url : "http://api.map.baidu.com/place/detail?uid=87248d96c43561aa2806ce53&output=html&source=placeapi_v2",
 * 				price : "88",
 * 				overall_rating : "4.0",
 * 				image_num : "278",
 * 				groupon_num : 3,
 * 				comment_num : "1795"
 * 			}
 * 		}
 * 	]
 * }
 * 
 * @author  TengTeng
 * @date      2013-10-4
 * @version 1.0
 */
public class Baidu implements Serializable, Comparable<Baidu> {
	
	private static final long serialVersionUID = 1L;
	
	// 名称
	public String name;
	// 经度
	public double location_lat = 0;
	// 纬度
	public double location_lng = 0;
	// 地址
	public String address;
	// 电话
	public String telephone;
	// 百度uid
	public String uid;
	// 当前位置距离
	public String distance;
	// poi的详情页URL
	public String detail_url;
	// 均价
	public String price;
	// 总体评分
	public String overall_rating;
	// 图片数
	public String image_num;
	// 评论数
	public String comment_num;
	// 与指定经纬度的距离
	private double relaDistance = 0;
	// 描述
	private String disc;
	
	// 缩略图
	public String shop_pic_soulue;
	
	// 描述Tittle
	private String disc_tittle;
	
	public Baidu() {
		super();
	}
	
	public Baidu(String name, double location_lat, double location_lng,
			String address, String telephone, String uid, String distance,
			String detail_url, String price, String overall_rating, String shop_pic_soulue,
			String image_num, String comment_num) {
		super();
		this.name = name;
		this.location_lat = location_lat;
		this.location_lng = location_lng;
		this.address = address;
		this.telephone = telephone;
		this.uid = uid;
		this.distance = distance;
		this.detail_url = detail_url;
		this.price = price;
		this.overall_rating = overall_rating;
		this.image_num = image_num;
		this.comment_num = comment_num;
		this.shop_pic_soulue = shop_pic_soulue;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLocation_lat() {
		return location_lat;
	}
	public void setLocation_lat(double location_lat) {
		this.location_lat = location_lat;
	}
	public double getLocation_lng() {
		return location_lng;
	}
	public void setLocation_lng(double location_lng) {
		this.location_lng = location_lng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public double getRelaDistance() {
		return relaDistance;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getDisc_tittle() {
		return disc_tittle;
	}
	public void setDisc_tittle(String disc_tittle) {
		this.disc_tittle = disc_tittle;
	}
	public String getShop_pic_soulue() {
		return shop_pic_soulue;
	}
	public void setShop_pic_soulue(String shop_pic_soulue) {
		this.shop_pic_soulue = shop_pic_soulue;
	}

	public void setRelaDistance(double lan, double lon) {
		// 计算经纬度的距离
		this.relaDistance = CommonUtil.distanceOfTwoPoints(location_lng, location_lat, lon, lan, GaussSphere.Beijing54);
	}

	@Override
	public Baidu clone() {
		return new Baidu(this.name, 
				this.location_lat, 
				this.location_lng, 
				this.address, 
				this.telephone, 
				this.uid, 
				this.distance, 
				this.detail_url, 
				this.price, 
				this.overall_rating, 
				this.image_num, 
				this.shop_pic_soulue,
				this.comment_num);
	}
	
	public void getDataFromMKPoiInfo(MKPoiInfo poiInfo) {
		
		this.name = poiInfo.name;
		this.address = poiInfo.address;
		this.location_lat = poiInfo.pt.getLatitudeE6();
		this.location_lng = poiInfo.pt.getLongitudeE6();
	}

	@Override
	public int compareTo(Baidu another) {
		if(this.relaDistance != another.getRelaDistance()){
		     return (int)Math.ceil(this.relaDistance-another.getRelaDistance());
		    }
		return 0;
	}
	
}
