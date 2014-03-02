/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.BusSavingModle;
import com.xundian360.huaqiaotong.modle.b00.Station;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * @author  TeneTeng
 * @date      2014-2-25
 * @version 1.0
 */
public class B00V00HisZdAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b00v00ZdName", "b00v00ZdLocation", "b00v00ZdBuses"};
	
	public static final int[] to = {R.id.b00v00ZdName, R.id.b00v00ZdLocation, R.id.b00v00ZdBuses,};
	
	List<Station> hisStations;
	
	List<Map<String, String>> data;
	
	Context context;
	
	// 存储历史记录
	BusSavingModle busSaving;

	public B00V00HisZdAdapter(Context context, List<Station> hisStations,
			List<Map<String, String>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		
		this.hisStations = hisStations;
		this.context = context;
		this.data = data;
		
		busSaving = new BusSavingModle(context);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		// 置顶按钮
		Button zhidingBtn = (Button) view.findViewById(R.id.b00v00ZdZdBtn);
		zhidingBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Map<String, String> dateItem = data.get(position);
				Station station = hisStations.get(position);
				
				data.remove(position);
				hisStations.remove(position);
				
				data.add(0, dateItem);
				hisStations.add(0, station);
				
				busSaving.read();
				List<String> stationNames = new ArrayList<String>(
						Arrays.asList(busSaving.getZhandianNames().split(BusSavingModle.SEPARATOR)));
				String stationName = stationNames.get(position);
				stationNames.remove(position);
				stationNames.add(0, stationName);
				
				StringBuffer stationIdsB = new StringBuffer();
				StringBuffer stationNamesB = new StringBuffer();
				
				for (int i = 0; i < hisStations.size(); i++) {
					stationIdsB.append(hisStations.get(i).getStationId() + BusSavingModle.SEPARATOR);
					stationNamesB.append(stationNames.get(i) + BusSavingModle.SEPARATOR);
				}
				
				// 移除最后一个分隔符
				if(stationIdsB.length() > 0) {
					busSaving.setZhandianIds(stationIdsB.substring(0, stationIdsB.length() - 1));
					busSaving.setZhandianNames(stationNamesB.substring(0, stationNamesB.length() - 1));
				}
				
				busSaving.save();
				
				// 更新视图
				notifyDataSetChanged();
			}
		});
		
		// 删除按钮
		Button shanchuBtn = (Button) view.findViewById(R.id.b00v00ZdDlBtn);
		shanchuBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				data.remove(position);
				hisStations.remove(position);
				
				busSaving.read();
				
				// 加载存储的历史记录
				String[] stationIds = busSaving.getZhandianIds().split(BusSavingModle.SEPARATOR);
				String[] stationNames = busSaving.getZhandianNames().split(BusSavingModle.SEPARATOR);
				StringBuffer stationIdsB = new StringBuffer();
				StringBuffer stationNamesB = new StringBuffer();
				
				for (int i = 0; i < hisStations.size(); i++) {
					stationIdsB.append(stationIds[i] + BusSavingModle.SEPARATOR);
					stationNamesB.append(stationNames[i] + BusSavingModle.SEPARATOR);
				}
				
				// 移除最后一个分隔符
				if(stationIdsB.length() > 0) {
					busSaving.setZhandianIds(stationIdsB.substring(0, stationIdsB.length() - 1));
					busSaving.setZhandianNames(stationNamesB.substring(0, stationIdsB.length() - 1));
				}else {
					busSaving.setZhandianIds("");
					busSaving.setZhandianNames("");
				}
				
				busSaving.save();
				
				// 更新视图
				notifyDataSetChanged();
			}
		});
		
		return view;
	}
	
}
