/**
 * 
 */
package com.xundian360.huaqiaotong.modle.map;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 公交路线搜索结果子类
 * @author  Administrator
 * @date      2013年11月8日
 * @version 1.0
 * 
 */
public class HQTTransitRouteResult implements Serializable {
	private static final long serialVersionUID = 134001908779693853L;
	
	// 开始节点
	private HQTPlanNode startNode;
	// 结束节点
	private HQTPlanNode endNode;
	// 打车价格
	private int TaxiPrice;
	
	// 公交方案详情
	ArrayList<HQTTransitRoutePlan> routePlans;
	
	public HQTTransitRouteResult() {
	}
}
