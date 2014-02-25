/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b02.B02V02Activity;
import com.xundian360.huaqiaotong.modle.b02.Bick;

/**
 * 自行车Adapter
 * 
 * @author LiZhenteng
 * @date 2013年10月15日
 * @version 1.0
 */
public class B02V02BickAdapter extends SimpleAdapter {

	public static final String[] from = { "b02v02ItemBickImg",
			"b02v02ItemBickAddress", "b02v02ItemBickAllNum",
			"b02v02ItemBickLastNum", "b02v02ItemBickDistance" };

	public static final int[] to = { R.id.b02v02ItemBickImg,
			R.id.b02v02ItemBickAddress, R.id.b02v02ItemBickAllNum,
			R.id.b02v02ItemBickLastNum, R.id.b02v02ItemBickDistance };

	ArrayList<Bick> bickListData;

	Activity context;

	public B02V02BickAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, ArrayList<Bick> bickListData) {
		super(context, data, resource, from, to);

		this.context = (Activity) context;
		this.bickListData = bickListData;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		// 下一级菜单
		ImageView toSubArrow = (ImageView) view
				.findViewById(R.id.b02v02ItemArrow);
		toSubArrow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent in = context.getIntent();
				in.putExtra(B02V02Activity.SINGLE_RETURN_KEY,
						bickListData.get(position).getUid());

				// 设置跳转参数
				context.setResult(B02V02Activity.SINGLE_RETURN_CODE, in);

				// 页面返回
				context.finish();
			}
		});

		return view;
	}

}
