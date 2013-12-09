/**
 * 
 */
package com.xundian360.huaqiaotong.modle.b00;

import java.io.Serializable;
import java.util.List;

import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 站点信息（网络信息）
 * 
 * <disc>
 * 请求：http://221.6.86.251:8899/GetBusStation?busStation=24// busStiation代表站台id【注：从站台表中取得站台Id]】
	响应的json格式解释：
	{"stopId ":214,        	  //1.1正在查询的站台id
	"busStopName":"徐公桥" 	  //1.2正在查询的站台名称
	 "reportInfoList ":[   	  //1.3公交车实况信息数组
		{ "routeId":3,			//2.1 公交车所属的线路id 
		"routeName":"102路",	  	//2.2公交车所属线路名称
		 "lastName":"安亭",		//2.3开往的终点站
		" stationInfoNoList":[   		//2.4该线路中多辆公交位置信息
			 {"itemIndex":"1",			//3.1该线路中第几辆公交车
			"nextId":"5",			 	//3.2下一站站台id
			"nextName":"双华路",   			//3.3下一站站台名称
			 "stopCount":1,				//3.4 公交车即将抵达目标站的剩余几站
			"distance":169,				//3.5 公交车即将抵达目标站的距离
			"time":0,				//3.6 公交车即将抵达目标站的时间
			"licence":“苏E-M9300"},			//3.7 公交车车牌号
			{"itemIndex":"2",			
			"nextId":"5",			 	
			"nextName":"双华路", 				
			 "stopCount":1,			
			"distance":169,			
			"time":0,
			"licence":"苏E-M9300"},
			...
		]
		},
		 …
	]
	}
	
	备注：
		1、剩余站台数（stopCount）为1001时,代表未发车
		2、剩余站台数（stopCount）为1002时,代表今日/运营结束
		3、剩余站台数（stopCount）为1003时,代表查询中
 * 
 * </disc>
 * 
 * @author  tengteng
 * @date      2013-12-1
 * @version 1.0
 */
public class NetStation implements Serializable, Comparable<NetStation> {
	
	private static final long serialVersionUID = 1L;
	
	private String routeId;
	private String routeName;
	private String lastName;
	
	private List<NetStationItem> stationItems;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<NetStationItem> getStationItems() {
		return stationItems;
	}

	public void setStationItems(List<NetStationItem> stationItems) {
		this.stationItems = stationItems;
	}

	@Override
	public int compareTo(NetStation another) {
		if(this.routeId != another.getRouteId()){
		     return (int)Math.ceil(StringUtils.paseDouble(this.routeId, 0)-StringUtils.paseDouble(another.getRouteId(), 0));
		 }
		return 0;
	}

}
