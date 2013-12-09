/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ListView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b02.B02V01TexiAdapter;
import com.xundian360.huaqiaotong.modle.b02.Texi;
import com.xundian360.huaqiaotong.modle.com.Baidu;

/**
 * 叫车服务符合条件
 * @author    LiZhenteng
 * @date      2013年10月14日
 * @version   1.0
 */
public class B02V01Activity extends ComNoTittleActivity {
	
	public static final String B02V01ACTIVITY_CARPOINT = "b02v01activity_carpoint";
	
	// 当前车属于哪个集散点
	Baidu carPoint;
	
	// 出租车列表
	ListView taxiList;
	
	// 数据源
	B02V01TexiAdapter texiAdapter;
	List<Map<String, Object>> texiData = new ArrayList<Map<String,Object>>();
	List<Texi> texiItems = new ArrayList<Texi>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b02v01);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 设值数据源数据
		setAdapterData();
		
		// 初始化数据源
		texiAdapter = new B02V01TexiAdapter(this, texiData, R.layout.b02v01_item, 
				B02V01TexiAdapter.from, B02V01TexiAdapter.to, texiItems);
		
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		taxiList = (ListView) findViewById(R.id.b02v01TaxiList);
		taxiList.setAdapter(texiAdapter);
	}
	
	/**
	 * 设值数据源数据
	 */
	private void setAdapterData(){
		
		// 设值出租车数据
		setTexiData();
		
		// 单个出租车数据
		for (Texi texiItem : texiItems) {
			
			Map<String, Object> texiItemMap = new HashMap<String, Object>();
			
			texiItemMap.put(B02V01TexiAdapter.from[0], texiItem.getName());
			texiItemMap.put(B02V01TexiAdapter.from[1], R.drawable.common_favorate_img_0);
			texiItemMap.put(B02V01TexiAdapter.from[2], texiItem.getFavorateNum());
			texiItemMap.put(B02V01TexiAdapter.from[3], texiItem.getPlate());
			texiItemMap.put(B02V01TexiAdapter.from[4], getString(R.string.b02v01_call_text));
			
			// 添加到数据源
			texiData.add(texiItemMap);
		}
	}
	
	/**
	 * 设值出租车数据
	 */
	private void setTexiData() {
		for (int i = 0; i < 10; i++) {
			Texi texiItem = new Texi("万师傅", "上海大众", "苏E12345", "15000654736", "+152");
			texiItems.add(texiItem);
		}
	}
}
