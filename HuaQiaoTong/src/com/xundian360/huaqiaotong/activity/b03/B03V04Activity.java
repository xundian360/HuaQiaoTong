/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;

import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.MultiColumnListView.OnLoadMoreListener;
import com.lurencun.android.adapter.AbstractAdapter;
import com.lurencun.android.adapter.CommonAdapter;
import com.lurencun.android.system.ActivityUtil;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.view.b02.SimpleViewBuilder;

/**
 * 论坛首页
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B03V04Activity extends ComNoTittleActivity {
	
	// 帖子列表
	MultiColumnListView tieList;
	
	// 数据源
	AbstractAdapter<Posts> adapter;
	
	// 帖子对象
	List<Posts> posts = new ArrayList<Posts>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b03v04);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		
		 // 设置帖子
		setPosts();
		
		// 设置数据源
		adapter = new CommonAdapter<Posts>(getLayoutInflater(), new SimpleViewBuilder(this));
		adapter.update(posts);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		tieList = (MultiColumnListView) findViewById(R.id.b03v04TieList);
		tieList.setAdapter(adapter);
		
		// 设置下拉时间
		tieList.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				adapter.add(posts);
				ActivityUtil.show(B03V04Activity.this, "到List底部自动加载更多数据");
				//5秒后完成
				new Handler().postDelayed(new Runnable(){
					@Override
					public void run() {
						tieList.onLoadMoreComplete();
					}
				}, 5000);
			}
		});
		
	}

	/**
	 * 设置帖子对象
	 */
	private void setPosts() {
		
		for (int i = 0; i < 15; i++) {
			Posts post = new Posts();
			
			post.setTittle("超级赛亚人");
			post.setCommentN("100");
			post.setImg("http://img02.store.sogou.com/app/a/07/b66c407c8b22faf84b98026501895cc8");
			
			posts.add(post);
		}
	}
}
