/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.b03.B03v00Util;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 帖子明细
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V01Activity extends ComNoTittleActivity {
	
	public static final String POST_KEY = "post_key";
	
	ImageView retBtn;
	
	LinearLayout detailContainer;
	
	// 帖子对象
	Posts posts;
	
	// 帖子子对对象
	List<PostsItem> postsItems = new ArrayList<PostsItem>();
	
	Handler _handler = new Handler();
	
	CommonProgressDialog processDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v01);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		posts = (Posts) getIntent().getSerializableExtra(POST_KEY);
		
		processDialog = new CommonProgressDialog(this);
		processDialog.show();
		
		// 设置数据
		new Thread(setData).start();
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
	Runnable setData = new Runnable() {
		
		@Override
		public void run() {

			try {
				postsItems = B03v00Util.getPostsDetailData(B03V01Activity.this, posts.getUid());
			} catch (JSONException e) {
				e.printStackTrace();
				_handler.post(getMsgError);
			}
			
			// 设置帖子明细
			_handler.post(setDetailContainer);
		}
	};
	
	/**
	 * 设置帖子明细
	 */
	Runnable setDetailContainer = new Runnable() {
		
		@Override
		public void run() {
			
			// 隐藏Dialog
			processDialog.dismiss();
			
			if (postsItems != null && postsItems.size() > 0) {
				
				// 设置明细
				for (PostsItem postsItem : postsItems) {
					
					// 文本
					if (PostsItem.TXT_KEY.equals(postsItem.getType())) {
						TextView textView = new TextView(B03V01Activity.this);
						textView.setText(postsItem.getMsg());
						textView.setTextColor(Color.WHITE);
						
						// 添加文本到页面显示
						detailContainer.addView(textView);
					}
					
					// 图片
					else if(PostsItem.IMG_KEY.equals(postsItem.getType())) {
						ImageView img1 = new ImageView(B03V01Activity.this);
						ImageLoader.getInstance().displayImage(postsItem.getMsg(), img1);
						
						// 添加图片到页面显示
						detailContainer.addView(img1);
					}
				}
			}
		}
	};
	
	/**
	 * 取得信息失败
	 */
	Runnable getMsgError = new Runnable() {
		
		@Override
		public void run() {
			ShowMessageUtils.show(B03V01Activity.this, "取得数据失败");
			processDialog.dismiss();
		}
	};
}
