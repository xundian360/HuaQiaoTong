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
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.common.db.b00.BusOperatingHelper;
import com.xundian360.huaqiaotong.modle.b00.Bus;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;

/**
 * 首页线路Adapter
 * 
 * @author TengTeng
 * @date 2013-9-25
 * @version 1.0
 */
public class B00v00XianluAdapter extends SimpleAdapter {

	// Key
	public static final String[] from = { "b00v00XianluItemName",
			"b00v00XianluItemDir", "b00v00XianluSaveFlag" };

	// Key ID
	public static final int[] to = { R.id.b00v00XianluItemName,
			R.id.b00v00XianluItemDir, R.id.b00v00XianluSaveFlag };

	Context context;

	// 显示的线路信息
	List<Bus> buses;

	// 数据库操作类
	BusOperatingHelper busDbHelper;

	public B00v00XianluAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Bus> buses) {
		super(context, data, resource, from, to);

		this.context = context;
		this.buses = buses;

		busDbHelper = new BusOperatingHelper(context);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// 取得Item View
		View view = super.getView(position, convertView, parent);

		// 收藏按钮
		ImageView shoucangImg = (ImageView) view
				.findViewById(R.id.b00v00XianluSaveFlag);

		// 判断是否收藏，设值图片状态
		if (buses.get(position).isSave()) {
			shoucangImg.setImageResource(R.drawable.b00v02_shuocang_btn);
		} else {
			shoucangImg.setImageResource(R.drawable.b00v02_no_shuocang_btn);
		}

		// 设值点击事件
		shoucangImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 当前站点信息
				Bus bus = buses.get(position);

				// 判断是否收藏
				if (bus.isSave()) {

					// 取消收藏
					bus.setSave(false);
					// 更新数据库
					busDbHelper.updateBus(bus);
					// 设值图片状态
					((ImageView) v)
							.setImageResource(R.drawable.b00v02_no_shuocang_btn);

					ShowMessageUtils.show(context,
							R.string.msg_no_shoucang_cancel);

				} else {
					// 收藏
					bus.setSave(true);
					// 更新数据库
					busDbHelper.updateBus(bus);
					// 设值图片状态
					((ImageView) v)
							.setImageResource(R.drawable.b00v02_shuocang_btn);

					ShowMessageUtils.show(context,
							R.string.msg_shoucang_success);
				}
			}
		});

		return view;
	}

}
