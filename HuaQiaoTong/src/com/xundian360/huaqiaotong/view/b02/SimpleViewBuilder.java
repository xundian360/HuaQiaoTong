package com.xundian360.huaqiaotong.view.b02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.lurencun.android.adapter.ViewBuilder;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V01Activity;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 创建View
 * 
 */
public class SimpleViewBuilder extends ViewBuilder<Posts> {
	
	Context context;
	
	public SimpleViewBuilder(Context context) {
		super();
		
		this.context = context;
	}

	@Override
	public View createView(LayoutInflater inflater, int position,
			Posts data) {
		View view = inflater.inflate(R.layout.b03v04_item, null);
		view.setOnClickListener(viewClick);
		updateView(view, position, data);
		return view;
	}
	
	/**
	 * 点击事件
	 */
	OnClickListener viewClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(context, B03V01Activity.class, 100);
		}
	};
	

	@Override
	public void updateView(View view, int position, Posts data) {
//		
//		// 背景
//		LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.b03v04MainLayout);
//		
//		// 生产随机数(1-4)，动态设置背景
//		int random =  (int)(Math.random()*4)+1;
//		
//		int bg_color = 0;
//		
//		switch (random) {
//		case 1:
//			bg_color = context.getResources().getColor(R.color.b03_v01_item_color_3);
//			break;
//		case 2:
//			bg_color = context.getResources().getColor(R.color.b03_v01_item_color_1);
//			break;
//		case 3:
//			bg_color = context.getResources().getColor(R.color.b03_v01_item_color_5);
//			break;
//		case 4:
//			bg_color = context.getResources().getColor(R.color.b03_v01_item_color_6);
//			break;
//
//		default:
//			bg_color = context.getResources().getColor(R.color.b03_v01_item_color_6);
//			break;
//		}
//		
//		mainLayout.setBackgroundColor(bg_color);
		
		
	}
}
