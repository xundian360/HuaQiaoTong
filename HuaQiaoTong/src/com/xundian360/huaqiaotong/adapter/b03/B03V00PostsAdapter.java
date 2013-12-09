/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b03;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pagesuite.flowtext.FlowTextView;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V01Activity;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 帖子对象
 * @author  TengTeng
 * @date      2013年10月28日
 * @version 1.0
 */
public class B03V00PostsAdapter extends SimpleAdapter {
	
	public static final String[] from = {"b03v00ItemTittle", "b03v00ItemAuthor", "b03v00ItemCommentNum"};
	
	public static final int[] to = {R.id.b03v00ItemTittle, R.id.b03v00ItemAuthor, R.id.b03v00ItemCommentNum};
	
	Context context;
	
	// 帖子对象List
	List<Posts> postses;

	public B03V00PostsAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, List<Posts> postses) {
		
		super(context, data, resource, from, to);
		
		this.context = context;
		this.postses = postses;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);
		
		String msgText = "<!--PSTYLE=WBDY_Web bodytext--><p>" + postses.get(position).gettDtail() + "</p>";
		
		// 设置缩略消息
		FlowTextView detailM = (FlowTextView) view.findViewById(R.id.b03v00ItemFlowText);		
		Spanned spannable = Html.fromHtml(msgText);	
		detailM.setText(spannable);		
		detailM.invalidate();
		
		detailM.setTextSize(30);
		
		// 设置图片
		ImageView itemImg = (ImageView) view.findViewById(R.id.b03v00ItemImg);
		ImageLoader.getInstance().displayImage(postses.get(position).getImg(), itemImg);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CommonUtil.startActivityForResult(context, B03V01Activity.class, 100);
			}
		});
		
		return view;
	}

}
