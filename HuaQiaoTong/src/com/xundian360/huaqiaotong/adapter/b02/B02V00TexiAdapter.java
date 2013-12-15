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
 * @author    LiZhenteng
 * @date      2013年10月15日
 * @version   1.0
 */
public class B02V00TexiAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b02v00ItemName", "b02v00ItemTime"};
	
	public static final int[] to = {R.id.b02v00ItemName, R.id.b02v00ItemTime};
	
	List<Texi> texiItems;
	
	Context context;

	public B02V00TexiAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Texi> texiItems) {
		super(context, data, resource, from, to);
		
		this.context = context;
		this.texiItems = texiItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		final String callNum = texiItems.get(position).getPhone();
		
		// 打电话
		TextView callTxte = (TextView) view.findViewById(R.id.b02v00ItemPhone);
		
		callTxte.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CommonUtil.callPhone(context, callNum);
			}
		});
		
		return view;
	}

}
