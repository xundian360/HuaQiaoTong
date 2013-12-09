/**
 * 
 */
package com.xundian360.huaqiaotong.util.b00;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.xundian360.huaqiaotong.common.map.MySearchListener;
import com.xundian360.huaqiaotong.view.b00.B00v00HuanchengView;

/**
 * 换乘监听
 * @author  Administrator
 * @date      2013年10月30日
 * @version 1.0
 */
public class B00v00HCSearchListener extends MySearchListener {
	
	Context context;
	
	// 换乘View
	B00v00HuanchengView hcView;
	
	public B00v00HCSearchListener(Context context, B00v00HuanchengView hcView) {
		this.context = context;
		this.hcView = hcView;
	}

	/**
	 * 兴趣点检索
	 */
	@Override
	public void onGetPoiResult(MKPoiResult result, int type, int iError) {
	
		// 错误号可参考MKEvent中的定义
		if (iError == MKEvent.ERROR_RESULT_NOT_FOUND){
			Toast.makeText(context, "抱歉，未找到结果",Toast.LENGTH_LONG).show();
			return ;
		}
		else if (iError != 0 || result == null) {
			Toast.makeText(context, "搜索出错啦..", Toast.LENGTH_LONG).show();
			return;
		}
		
		// 设置兴趣点
		ArrayList<MKPoiInfo> searchPois = result.getAllPoi();
		
		if(searchPois != null && searchPois.size() > 0) {
			hcView.refreshPoiList(searchPois);
		}
		
		super.onGetPoiResult(result, type, iError);
	}
}
