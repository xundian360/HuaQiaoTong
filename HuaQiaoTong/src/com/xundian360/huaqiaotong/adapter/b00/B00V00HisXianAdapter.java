/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b00;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.modle.b00.BusSavingModle;

/**
 * @author  TeneTeng
 * @date      2014-2-25
 * @version 1.0
 */
public class B00V00HisXianAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b00v00XlNum", "b00v00XlDir", "b00v00XlStatus"};
	
	public static final int[] to = {R.id.b00v00XlNum, R.id.b00v00XlDir, R.id.b00v00XlStatus,};
	
	List<Bus> hisBuses;
	
	List<Map<String, String>> data;
	
	Context context;
	
	// 存储历史记录
	BusSavingModle busSaving;

	public B00V00HisXianAdapter(Context context, List<Bus> hisBuses,
			List<Map<String, String>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		
		this.hisBuses = hisBuses;
		this.context = context;
		this.data = data;
		
		busSaving = new BusSavingModle(context);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		// 设置站点View颜色
		TextView xlNum = (TextView) view.findViewById(R.id.b00v00XlNum);
		if(position % 2 == 0) {
			xlNum.setTextColor(context.getResources().getColor(R.color.b00_v00_his_tittle_color_1));
		} else {
			xlNum.setTextColor(context.getResources().getColor(R.color.b00_v00_his_tittle_color_2));
		}
		
		// 置顶
		Button zhidingBtn = (Button) view.findViewById(R.id.b00v00XlZdBtn);
		zhidingBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Map<String, String> dateItem = data.get(position);
				Bus bus = hisBuses.get(position);
				
				data.remove(position);
				hisBuses.remove(position);
				
				data.add(0, dateItem);
				hisBuses.add(0, bus);
				
				busSaving.read();
				
				// 加载存储的历史记录
				StringBuffer xianluIdsB = new StringBuffer();
				
				for (int i = 0; i < hisBuses.size(); i++) {
					xianluIdsB.append(hisBuses.get(i).getRouteId() + BusSavingModle.SEPARATOR);
				}
				
				// 移除最后一个分隔符
				if(xianluIdsB.length() > 0) {
					busSaving.setXianluIds(xianluIdsB.substring(0, xianluIdsB.length() - 1));
				}
				
				busSaving.save();
				
				notifyDataSetChanged();
			}
		});
		
		// 删除按钮
		Button shanchuBtn = (Button) view.findViewById(R.id.b00v00XlDlBtn);
		shanchuBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				data.remove(position);
				hisBuses.remove(position);
				
				busSaving.read();
				
				// 加载存储的历史记录
				StringBuffer xianluIdsB = new StringBuffer();
				
				for (int i = 0; i < hisBuses.size(); i++) {
					xianluIdsB.append(hisBuses.get(i).getRouteId() + BusSavingModle.SEPARATOR);
				}
				
				// 移除最后一个分隔符
				if(xianluIdsB.length() > 0) {
					busSaving.setXianluIds(xianluIdsB.substring(0, xianluIdsB.length() - 1));
				} else {
					busSaving.setXianluIds("");
				}
				
				busSaving.save();
				
				notifyDataSetChanged();
			}
		});
		
		return view;
	}
	
}
