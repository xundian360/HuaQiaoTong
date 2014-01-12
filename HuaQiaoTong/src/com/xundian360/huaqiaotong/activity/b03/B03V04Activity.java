/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.huewu.pla.lib.MultiColumnListView;
import com.lurencun.android.adapter.AbstractAdapter;
import com.lurencun.android.adapter.CommonAdapter;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.b03.PostGroup;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.UserUtils;
import com.xundian360.huaqiaotong.view.b02.SimpleViewBuilder;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;

/**
 * 帖子列表页
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V04Activity extends ComNoTittleActivity {
	
	public static final String POST_GROUP_KEY = "post_group_key";
	
	// 标题
	TextView tittleView;
	// 发帖按钮
	TextView sendPostBtn;
	// 帖子列表
	MultiColumnListView tieList;
	// 数据源
	AbstractAdapter<Posts> adapter;
	// 帖子对象
	List<Posts> posts = new ArrayList<Posts>();
	// 帖子列表所属组
	PostGroup postGroup;
	
	// 进度条
	CommonProgressDialog processDialog;
	
	Handler _handler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v04);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		 // 设置帖子
		setPosts();
		
		// 刷新View
		adapter.update(posts);
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		processDialog = new CommonProgressDialog(this);
		processDialog.show();
		
		postGroup = (PostGroup) getIntent().getSerializableExtra(POST_GROUP_KEY);
		
		// 设置数据源
		adapter = new CommonAdapter<Posts>(getLayoutInflater(), new SimpleViewBuilder(this));
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		tittleView = (TextView) findViewById(R.id.b03v04Tittle);
		tittleView.setText(postGroup.getGroupName());
		
		sendPostBtn = (TextView) findViewById(R.id.b03v04AddBtn);
		sendPostBtn.setOnClickListener(sendPostBtnClick);
		
		tieList = (MultiColumnListView) findViewById(R.id.b03v04TieList);
		tieList.setAdapter(adapter);
		
//		// 设置下拉时间
//		tieList.setOnLoadMoreListener(new OnLoadMoreListener() {
//			@Override
//			public void onLoadMore() {
//				adapter.add(posts);
//				ActivityUtil.show(B03V04Activity.this, "到List底部自动加载更多数据");
//				//5秒后完成
//				new Handler().postDelayed(new Runnable(){
//					@Override
//					public void run() {
//						tieList.onLoadMoreComplete();
//					}
//				}, 5000);
//			}
//		});
	}
	
	/**
	 * 发帖按钮点击
	 */
	OnClickListener sendPostBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// 判断用户是否登录
			if(UserUtils.isLogin(B03V04Activity.this)) {
				// 发帖
				CommonUtil.startActivityForResult(B03V04Activity.this, 
						B03V02Activity.class, 
						POST_GROUP_KEY, 
						postGroup, 
						1000);
			} else {
				ShowMessageUtils.show(B03V04Activity.this, R.string.b04v00_login_msg);
				// 登陆
				CommonUtil.startSubActivity(B03V04Activity.this, B04V00Activity.class);
			} 
		}
	};

	/**
	 * 设置帖子对象
	 */
	private void setPosts() {
		
		// 取得帖子数据
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
	}
	
	
}
