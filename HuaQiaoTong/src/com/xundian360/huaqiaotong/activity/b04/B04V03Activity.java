/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.BaiduComment;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.UserUtils;

/**
 * 个人中心
 * 
 * @author Administrator
 * @date 2013年10月23日
 * @version 1.0
 */
public class B04V03Activity extends ComNoTittleActivity {

	public static final String USER_COMMENT_KEY = "USER_COMMENT_KEY";

	// 返回按钮
	ImageButton retBtn1;
	// 标题
	TextView tittleText;
	// 用户图像
	ImageView logoImg;
	// 发表新帖
	LinearLayout editePostsBtn;
	// 朋友列表
	LinearLayout friendListBtn;
	// 用户描述
	TextView userDisc;
	// 显示更多
	LinearLayout showMoreBtn;
	// 评论
	TextView commentNum;
	// 评论列表
	ListView commentList;
	// 底部按钮容器
	LinearLayout bottomLayout;
	// 返回按钮
	ImageButton retBtn;
	// 消息
	ImageButton msgBtn;
	// 附近按钮
	ImageButton locationBtn;

	// 用户存储类
	UserModle userModle;

	// 其他用户ID
	BaiduComment userComment;

	// 图片缓存
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.b04v03_user_default_logo)
			.showImageForEmptyUri(R.drawable.b04v03_user_default_logo)
			.showImageOnFail(R.drawable.b04v03_user_default_logo)
			.cacheOnDisc(true).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.b04v03);

		// 初始化组件
		initModule();

		// 初始化数据
		initData();
	}

	@Override
	protected void onResume() {

		String logoPath;

		// 进个人中心
		if (userComment == null) {
			// 更新数据
			userModle.read();

			// 设置描述
			if (StringUtils.isNotBlank(userModle.user.getDisc())) {
				userDisc.setText(userModle.user.getDisc());
			} else {
				userDisc.setText(R.string.b04v03_text_user_disc_default);
			}

			// 用户图片路径
			logoPath = userModle.user.getLogoPath().replace(
					UserUtils.USER_ICON_200, UserUtils.USER_ICON_170);

		} else {

			// 标题
			tittleText.setText(userComment.getUserName());
			// 已发表帖子数量
			commentNum.setText(getString(R.string.b04v03_text_commont_num2,
					"20"));

			// 设置编辑按钮隐藏
			showMoreBtn.setVisibility(View.GONE);
			bottomLayout.setVisibility(View.GONE);

			// 用户图片路径
			logoPath = userComment.getUserLogoPath().replace(
					UserUtils.USER_ICON_200, UserUtils.USER_ICON_64);
		}

		// 设置人物图片
		ImageLoader.getInstance().displayImage(logoPath, logoImg, options);

		super.onResume();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		userModle = new UserModle(this);
		userModle.read();

		// 查看其它用户信息
		userComment = (BaiduComment) getIntent().getSerializableExtra(
				USER_COMMENT_KEY);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		retBtn1 = (ImageButton) findViewById(R.id.b04v03RetBtn);
		retBtn1.setOnClickListener(retBtnClick);

		tittleText = (TextView) findViewById(R.id.b04v03Tittle);

		logoImg = (ImageView) findViewById(R.id.b04v03UserLogoImg);
		userDisc = (TextView) findViewById(R.id.b03v02UserDisc);
		showMoreBtn = (LinearLayout) findViewById(R.id.b04v03ShowMoreBtn);
		showMoreBtn.setOnClickListener(showMoreBtnClick);

		commentNum = (TextView) findViewById(R.id.b04v03CommentNum);
		commentNum.setText(getString(R.string.b04v03_text_commont_num, "20"));

		commentList = (ListView) findViewById(R.id.b04v03CommentList);
		retBtn = (ImageButton) findViewById(R.id.b04v03ReturnBtn);
		retBtn.setOnClickListener(retBtnClick);

		msgBtn = (ImageButton) findViewById(R.id.b04v03MsgBtn);
		locationBtn = (ImageButton) findViewById(R.id.b04v03LocationBtn);

		bottomLayout = (LinearLayout) findViewById(R.id.b04v03BottomLayout);
	}

	/**
	 * 返回按钮
	 */
	OnClickListener retBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			onBackPressed();
		}
	};

	/**
	 * 详细信息
	 */
	OnClickListener showMoreBtnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B04V03Activity.this,
					B04V04Activity.class, 100);
		}
	};

}
