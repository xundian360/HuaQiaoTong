/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.adapter.b03.B03V04PostItems;
import com.xundian360.huaqiaotong.modle.b03.PostGroup;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.util.BaiduUtil;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.UserUtils;
import com.xundian360.huaqiaotong.util.b03.B03v00Util;
import com.xundian360.huaqiaotong.view.com.CommonProgressDialog;
import com.xundian360.huaqiaotong.view.com.XListView;
import com.xundian360.huaqiaotong.view.com.XListView.IXListViewListener;

/**
 * 帖子列表页
 * 
 * @author Administrator
 * @date 2013年10月23日
 * @version 1.0
 */
public class B03V04Activity extends ComNoTittleActivity {

	public static final String POST_GROUP_KEY = "post_group_key";

	// 标题
	TextView tittleView;
	// 发帖按钮
	TextView sendPostBtn;
	// 帖子列表
	XListView tieList;
	// 数据源
	B03V04PostItems adapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	// 帖子对象
	List<Posts> posts = new ArrayList<Posts>();
	// 帖子列表所属组
	PostGroup postGroup;

	// 进度条
	CommonProgressDialog processDialog;

	Handler _handler = new Handler();

	// 商店数量
	int totalNum = 0;
	// 第几页
	int pageNum = 0;

	int pageSize = 10;

	boolean canLoad = true;

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
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		processDialog = new CommonProgressDialog(this);
		processDialog.show();

		postGroup = (PostGroup) getIntent()
				.getSerializableExtra(POST_GROUP_KEY);

		// 设置数据源
		adapter = new B03V04PostItems(this, data, R.layout.b03v04_item,
				B03V04PostItems.fromKey, B03V04PostItems.idKey);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		tittleView = (TextView) findViewById(R.id.b03v04Tittle);
		tittleView.setText(postGroup.getGroupName());

		sendPostBtn = (TextView) findViewById(R.id.b03v04AddBtn);
		sendPostBtn.setOnClickListener(sendPostBtnClick);

		tieList = (XListView) findViewById(R.id.b03v04TieList);
		tieList.setPullLoadEnable(canLoad);
		tieList.setXListViewListener(itemListPush);
		tieList.setAdapter(adapter);
		tieList.setOnItemClickListener(postItemClick);

	}

	OnItemClickListener postItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			CommonUtil.startActivityForResult(B03V04Activity.this,
					B03V01Activity.class, B03V01Activity.POST_KEY,
					posts.get(arg2 - 1), 100);
		}
	};

	IXListViewListener itemListPush = new IXListViewListener() {

		@Override
		public void onRefresh() {
			_handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					tieList.stopRefresh();
					tieList.stopLoadMore();
				}
			}, 2000);

		}

		@Override
		public void onLoadMore() {
			_handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					tieList.stopRefresh();
					tieList.stopLoadMore();
				}
			}, 2000);
		}
	};

	/**
	 * 发帖按钮点击
	 */
	OnClickListener sendPostBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// 判断用户是否登录
			if (UserUtils.isLogin(B03V04Activity.this)) {
				// 发帖
				CommonUtil.startActivityForResult(B03V04Activity.this,
						B03V02Activity.class, POST_GROUP_KEY, postGroup, 1000);
			} else {
				ShowMessageUtils.show(B03V04Activity.this,
						R.string.b04v00_login_msg);
				// 登陆
				CommonUtil.startSubActivity(B03V04Activity.this,
						B04V00Activity.class);
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
				// 取得帖子数据
				Map<String, Object> postsDatas = B03v00Util.getPostsData(
						B03V04Activity.this, postGroup.getGroupId(), pageNum,
						pageSize);

				if (postsDatas == null || postsDatas.isEmpty()
						|| postsDatas.size() <= 0) {
					// 更新UI
					_handler.post(getMsgError);
					return;
				}

				// 取得帖子总数
				totalNum = StringUtils.paseInt(
						(String) postsDatas.get(BaiduUtil.TOTAL_KEY), 0);
				// 帖子列表
				@SuppressWarnings("unchecked")
				List<Posts> themeDiscs = (List<Posts>) postsDatas
						.get(BaiduUtil.RESULTS_KEY);

				// 取得数据为空
				if (totalNum <= 0 || themeDiscs == null || themeDiscs.isEmpty()
						|| themeDiscs.size() <= 0) {
					_handler.post(getMsgBlank);
					return;
				}

				if (pageNum == 0) {
					posts.clear();
				}

				// 添加到列表
				posts.addAll(themeDiscs);

				// 更新UI
				_handler.post(updateList);

			}
		}).start();
	}

	/**
	 * 更新ListView
	 */
	Runnable updateList = new Runnable() {

		@Override
		public void run() {

			// 从帖子对象在设置序列
			setArrayFromPosts();
			adapter.setPostsList(posts);

			// 刷新View
			adapter.notifyDataSetChanged();

			// 取消Dialog显示
			processDialog.dismiss();
		}
	};

	/**
	 * 从帖子对象在设置序列
	 */
	private void setArrayFromPosts() {
		data.clear();

		for (Posts post : posts) {
			Map<String, String> postItem = new HashMap<String, String>();

			postItem.put(B03V04PostItems.fromKey[0], post.getAuthorPic());
			postItem.put(B03V04PostItems.fromKey[1], post.getAuthor());
			postItem.put(B03V04PostItems.fromKey[2], post.getImg());
			postItem.put(B03V04PostItems.fromKey[3], post.getTittle());
			postItem.put(B03V04PostItems.fromKey[4],
					getString(R.string.b03v04_comm_num, post.getCommentN()));
			postItem.put(B03V04PostItems.fromKey[5], post.getTime());

			data.add(postItem);
		}
	}

	/**
	 * 取得信息失败
	 */
	Runnable getMsgError = new Runnable() {

		@Override
		public void run() {
			ShowMessageUtils.show(B03V04Activity.this, "取得数据失败");
			processDialog.dismiss();
		}
	};

	/**
	 * 取得信息为空
	 */
	Runnable getMsgBlank = new Runnable() {

		@Override
		public void run() {
			ShowMessageUtils.show(B03V04Activity.this, "未取到数据");
			processDialog.dismiss();
		}
	};
}
