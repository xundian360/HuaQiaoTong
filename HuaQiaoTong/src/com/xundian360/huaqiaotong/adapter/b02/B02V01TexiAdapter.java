/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b02;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b02.Texi;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 出租车Adapter
 * 
 * @author LiZhenteng
 * @date 2013年10月15日
 * @version 1.0
 */
public class B02V01TexiAdapter extends SimpleAdapter {

	public static final String[] from = { "b02v01ItemName",
			"b02v01ItemFavorateImg", "b02v01ItemFavorateNum",
			"b02v01ItemPlate", "b02v01ItemPhone" };

	public static final int[] to = { R.id.b02v01ItemName,
			R.id.b02v01ItemFavorateImg, R.id.b02v01ItemFavorateNum,
			R.id.b02v01ItemPlate, R.id.b02v01ItemPhone };

	List<Texi> texiItems;

	Context context;

	public B02V01TexiAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Texi> texiItems) {
		super(context, data, resource, from, to);

		this.context = context;
		this.texiItems = texiItems;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);

		// 打电话
		TextView callTxte = (TextView) view.findViewById(R.id.b02v01ItemPhone);

		callTxte.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommonUtil.callPhone(context, texiItems.get(position)
						.getPhone());
			}
		});

		return view;
	}

}
