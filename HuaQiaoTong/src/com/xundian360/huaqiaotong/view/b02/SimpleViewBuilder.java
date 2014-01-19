package com.xundian360.huaqiaotong.view.b02;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lurencun.android.adapter.ViewBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b03.B03V01Activity;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.util.CommonUtil;

/**
 * 创建View
 * 
 */
public class SimpleViewBuilder extends ViewBuilder<Posts> {
	
	Context context;
	
	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.test_user_logo)
			.showImageForEmptyUri(R.drawable.test_user_logo)
			.showImageOnFail(R.drawable.test_user_logo)
			.cacheOnDisc(true)
			.build();
	
	// 图片缓存
	DisplayImageOptions options2 = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.test_b03v04_img)
			.showImageForEmptyUri(R.drawable.test_b03v04_img)
			.showImageOnFail(R.drawable.test_b03v04_img)
			.cacheOnDisc(true)
			.build();
	
	// 帖子对象
	List<Posts> postsList;
	
	public SimpleViewBuilder(Context context, List<Posts> postsList) {
		super();
		
		this.context = context;
		this.postsList = postsList;
	}

	@Override
	public View createView(LayoutInflater inflater, final int position,
			Posts data) {
		View view = inflater.inflate(R.layout.b03v04_item, null);
		view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CommonUtil.startActivityForResult(context, 
							B03V01Activity.class, 
							B03V01Activity.POST_KEY, 
							postsList.get(position), 100);
				}
			});
		updateView(view, position, data);
		return view;
	}

	@Override
	public void updateView(View view, int position, Posts data) {
		
		// 帖子对象
		Posts posts = postsList.get(position);
		
		// 用户Logo
		ImageView userLogo = (ImageView) view.findViewById(R.id.b03v04UserLogo);
		ImageLoader.getInstance().displayImage(posts.getAuthorPic(), userLogo, options);
		
		// 发帖人
		TextView userName = (TextView) view.findViewById(R.id.b03v04UserName);
		userName.setText(posts.getAuthor());
		
		// 帖子图片
		ImageView postPicView = (ImageView) view.findViewById(R.id.b03v04ItemImg);
		ImageLoader.getInstance().displayImage(posts.getImg(), postPicView, options2);
		
		// 帖子Tittle
		TextView postTittle = (TextView) view.findViewById(R.id.b03v04ItemTittle);
		postTittle.setText(posts.getTittle());
		
		// 评论数
		TextView commentNum = (TextView) view.findViewById(R.id.b03v04ItemComNum);
		commentNum.setText(posts.getCommentN());
		
	}
	
	public void setPostsList(List<Posts> postsList) {
		this.postsList = postsList;
	}
}
