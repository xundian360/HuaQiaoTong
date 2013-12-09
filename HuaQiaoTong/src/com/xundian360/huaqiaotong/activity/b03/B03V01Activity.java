/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.List;

import org.json.JSONException;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.modle.b03.PostsItem;
import com.xundian360.huaqiaotong.util.b03.B03v00Util;

/**
 * 帖子明细
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V01Activity extends ComNoTittleActivity {
	
	ImageView retBtn;
	
	LinearLayout detailContainer;
	
	// 帖子对象
	Posts posts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v01);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
		
		// 设置帖子明细
		setDetailContainer();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		// 设置数据
		setData();
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		retBtn = (ImageView) findViewById(R.id.b03v01RetBtn);
		retBtn.setOnClickListener(returnBtnClick);
		
		detailContainer = (LinearLayout) findViewById(R.id.b03v01DetailContainer);
	}

	/**
	 * 返回
	 */
	OnClickListener returnBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};
	
	/**
	 * 设置数据
	 */
	private void setData() {
		
		String postsJson = "{\"status\":0,\"tittle\":\"花桥地铁开通了\",\"commentN\":\"152\",\"author\":\"鬼太郎\",\"time\":\"2013-10-23 24:52:59\",\"uid\":\"befdb29"
				+ "2767279f887154123\",\"dtailL\":[{\"type\":\"txt\",\"msg\":\"花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花"
				+ "桥地铁开通了\"},{\"type\":\"img\",\"msg\":\"http://img1.gtimg.com/7/791/79120/7912029_1200x1000_0.png\"},{\"type\":\"txt\",\"msg\":\"花桥地铁开通了"
				+ "花桥地铁开通了花桥地铁开通了"
				+ "花桥地铁开通了花桥地铁开通了\"},{\"type\":\"img\",\"msg\":\"http://img1.gtimg.com/7/791/79120/7912031_1200x1000_0.png\"}]}";
		
		try {
			posts = B03v00Util.getPostsDetailData(postsJson);
		} catch (JSONException e) {
			e.printStackTrace();
			// TODO
		}
	}
	
	/**
	 * 设置帖子明细
	 */
	private void setDetailContainer() {
		
		List<PostsItem> postsItems = posts.getDtailL();
		
		if (postsItems != null && postsItems.size() > 0) {
			
			// 设置明细
			for (PostsItem postsItem : postsItems) {
				
				// 文本
				if (PostsItem.TXT_KEY.equals(postsItem.getType())) {
					TextView textView = new TextView(this);
					textView.setText(postsItem.getMsg());
					textView.setTextColor(Color.WHITE);
					
					// 添加文本到页面显示
					detailContainer.addView(textView);
				}
				
				// 图片
				else if(PostsItem.IMG_KEY.equals(postsItem.getType())) {
					ImageView img1 = new ImageView(this);
					ImageLoader.getInstance().displayImage(postsItem.getMsg(), img1);
					
					// 添加图片到页面显示
					detailContainer.addView(img1);
				}
			}
		}
	}
}
