package com.xundian360.huaqiaotong.view.b00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 选择历史站点Dialog
 * @author TengTeng
 *
 */
public class B00SelectZhandianHistoryDialog extends Dialog {
	
	private String[] from = {"b00v00HistoryItemNum", "b00v00HistoryItemName"};
	
	private int[] to = {R.id.b00v00HistoryItemNum, R.id.b00v00HistoryItemName};
	
	private Context context;
	// 站点选择回调
	private OnZhandianHistorySelect call;
	
	// 站点列表
	private List<Station> stations;
	
	// 清除
	TextView clearHis;
	// 站点列表
	ListView hisStations;
	
	SimpleAdapter adapter;
	
	List<Map<String, String>> data = new ArrayList<Map<String,String>>();
	
	public B00SelectZhandianHistoryDialog(Context context,
			OnZhandianHistorySelect call, List<Station> stations) {
		
		super(context, R.style.b00v00HistoryDialogStyle);

		this.context = context;
		this.call = call;
		this.stations = stations;

		// 设置Dialog位置，实现多分辨率支持
		Window dialogWindow = getWindow();
//		// 获取屏幕宽、高用
//		Display d = dialogWindow.getWindowManager().getDefaultDisplay();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);

		// 新位置Y坐标（偏移）
		lp.y = CommonUtil.getDisplayHeight(dialogWindow)  / 6;

//		lp.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
		
		dialogWindow.setAttributes(lp);
		dialogWindow.setBackgroundDrawableResource(R.drawable.b00v00_history_dialog_bg);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.b00v00_history_zhandian);
        
        // 初始化数据
        initData();
        
        // 创建Dalog
        createView();
    }
    
    /**
     * 初始化数据
     */
    private void initData() {
    	
    	// 设置
    	for (int i = 0; i < stations.size(); i++) {
    		
    		Map<String, String> item = new HashMap<String, String>();
    		
    		item.put(from[0], i + "");
    		item.put(from[1], stations.get(i).getName());
    		
    		data.add(item);
		}
    	
    	// 设置站点数据源
    	adapter = new SimpleAdapter(context, data, R.layout.b00v00_history_zhandian_item, from, to);
    	
    }
	/*
	 * 创建Dalog
	 */
	private void createView() {
		
		clearHis = (TextView) findViewById(R.id.b00v00HistoryClear);
		hisStations = (ListView) findViewById(R.id.b00v00Historys);
		hisStations.setAdapter(adapter);
		hisStations.setOnItemClickListener(hisStationItemClick);
		
		// 设置ListView 的高度
		int listHigth = CommonUtil.getDisplayHeight(getWindow()) / 3;
		
		// 设置ListView位置
		RelativeLayout.LayoutParams _layout = 
				new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,  listHigth);
		
		_layout.setMargins(5, 5, 5, 0);
		
		// 位于线的下面
		_layout.addRule(RelativeLayout.BELOW, R.id.b00v00HistoryModdleLine);
		
		hisStations.setLayoutParams(_layout);
		
	}
	
	/**
	 * 站点项目点击
	 */
	OnItemClickListener hisStationItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			// 回调
			call.onZhandianSelect(stations.get(arg2));
		}
	};
	
	/**
	 * 站点选择回调
	 */
	interface OnZhandianHistorySelect {
		public void onZhandianSelect(Station station);
	}
	
	@Override
	public void show() {
		
		super.show();
	}
	
}
