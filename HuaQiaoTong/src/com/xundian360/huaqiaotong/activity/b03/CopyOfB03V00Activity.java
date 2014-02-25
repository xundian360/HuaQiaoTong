/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.b04.B04V00Activity;
import com.xundian360.huaqiaotong.activity.b04.B04V03Activity;
import com.xundian360.huaqiaotong.activity.com.CustomAnimation;
import com.xundian360.huaqiaotong.adapter.b03.B03V00NavAdapter;
import com.xundian360.huaqiaotong.adapter.b03.B03V00PostsAdapter;
import com.xundian360.huaqiaotong.modle.b03.Posts;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.util.b03.B03v00Util;

/**
 * 论坛首页
 * 
 * @author Administrator
 * @date 2013年10月23日
 * @version 1.0
 */
public class CopyOfB03V00Activity extends CustomAnimation {

	public static final int INT_FLING_MIN_DISTANCE = 25;

	public CopyOfB03V00Activity() {
		super(R.string.anim_scale, new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}
		});
	}

	// 设值按钮
	ImageButton setttingBtn;
	// 设值按钮
	ImageButton editeBtn;
	// 切换主题按钮
	ImageButton switchBtn;

	TextView tittleText;

	// 话题列表
	ListView talkList;

	// 置顶图片
	ViewFlipper topViews;

	// 置顶图片进度
	LinearLayout topViewsProcess;

	// 数据源
	B03V00PostsAdapter adapter;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	Map<String, Object> postsesAll = null;

	int totalAll = 0;

	// 帖子对象List
	List<Posts> postses = new ArrayList<Posts>();

	// 右边导航
	ExpandableListView navList;
	B03V00NavAdapter navListAdapter;
	List<Map<String, Object>> navListData = new ArrayList<Map<String, Object>>();
	List<List<Map<String, String>>> navListChildData = new ArrayList<List<Map<String, String>>>();

	// 用户Logo
	ImageView userLogo;
	// 登陆名
	TextView loginName;
	// 个人中心
	TextView selfCenter;

	// 用户存储类
	UserModle userModle;

	@Override
	protected void onStart() {

		userModle.read();

		// 判断是否登陆
		if (StringUtils.isNotBlank(userModle.user.getLoginName())) {

			// 设置用户名
			loginName.setText(userModle.user.getLoginName());
		} else {

			// 设置点击事件
			loginName.setOnClickListener(loginNameClick);
		}

		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

		setContentView(R.layout.b03v00);

		getSlidingMenu().setMenu(R.layout.b03v00_left);
		getSupportFragmentManager().beginTransaction().commit();

		getSlidingMenu().setSecondaryMenu(R.layout.b03v00_right);
		getSupportFragmentManager().beginTransaction().commit();

		// 初始化数据
		initData();

		// 初始化组件
		initModule();

		// 设置置顶图片数据
		setTopViewFilterData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {

		// 设置存储的用户数据
		userModle = new UserModle(this);

		// 设置数据
		getData();

		// 设置数据源
		getAdapterData();

		// 设置导航数据
		setNavAdapter();

		// 设置导航数据(第二层)
		setNavLevel2Adapter();

		adapter = new B03V00PostsAdapter(this, data, R.layout.b03v00_item,
				B03V00PostsAdapter.from, B03V00PostsAdapter.to, postses);

		// navListAdapter = new SimpleAdapter(this,
		// navListData,
		// R.layout.b03v00_nav_item,
		// B03V00NavAdapter.p_from,
		// B03V00NavAdapter.p_to);

		navListAdapter = new B03V00NavAdapter(this, navListData,
				R.layout.b00v02_xianlu_item, B03V00NavAdapter.p_from,
				B03V00NavAdapter.p_to, navListChildData,
				R.layout.b03v00_nav_level2_item, B03V00NavAdapter.from,
				B03V00NavAdapter.to);
	}

	/**
	 * 初始化组件
	 */
	private void initModule() {

		setttingBtn = (ImageButton) findViewById(R.id.b03v00SetttingBtn);
		setttingBtn.setOnClickListener(setttingBtnClick);

		editeBtn = (ImageButton) findViewById(R.id.b03v00EditeBtn);
		editeBtn.setOnClickListener(editeBtnClick);

		switchBtn = (ImageButton) findViewById(R.id.b03v00SwitchBtn);
		switchBtn.setOnClickListener(switchBtnClick);

		tittleText = (TextView) findViewById(R.id.b03v00Tittle);

		talkList = (ListView) findViewById(R.id.b03v00TalkList);
		talkList.setAdapter(adapter);
		talkList.setOnItemClickListener(talkListItemClick);

		topViews = (ViewFlipper) findViewById(R.id.b03v00TopViews);
		topViews.setOnTouchListener(topViewsTouch);

		topViewsProcess = (LinearLayout) findViewById(R.id.b03v00TopViewsProcess);

		navList = (ExpandableListView) findViewById(R.id.b03v00RightNavItems);
		navList.setAdapter(navListAdapter);

		userLogo = (ImageView) findViewById(R.id.b03v00ItemUserLogo);

		loginName = (TextView) findViewById(R.id.b03v00ItemLogin);

		selfCenter = (TextView) findViewById(R.id.b03v00ItemSelfCenter);
		selfCenter.setOnClickListener(selfCenterClick);

	}

	/**
	 * 登陆用户点击
	 */
	OnClickListener loginNameClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(CopyOfB03V00Activity.this,
					B04V00Activity.class, 100);
		}
	};

	/**
	 * 去个人中心
	 */
	OnClickListener selfCenterClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 判断是否登陆
			if (StringUtils.isNotBlank(userModle.user.getLoginName())) {
				CommonUtil.startActivityForResult(CopyOfB03V00Activity.this,
						B04V03Activity.class, 100);
			} else {
				CommonUtil.startActivityForResult(CopyOfB03V00Activity.this,
						B04V00Activity.class, 100);
			}
		}
	};

	/**
	 * Item点击事件
	 */
	OnItemClickListener talkListItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			CommonUtil.startActivityForResult(CopyOfB03V00Activity.this,
					B03V02Activity.class, 100);
		}
	};

	/**
	 * 设置
	 */
	OnClickListener setttingBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 显示左边栏
			showMenu();
		}
	};

	/**
	 * 发帖
	 */
	OnClickListener editeBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(CopyOfB03V00Activity.this,
					B03V02Activity.class, 100);
		}
	};

	/**
	 * 切换栏目
	 */
	OnClickListener switchBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 显示右边栏
			showSecondaryMenu();
		}
	};

	/**
	 * 置顶图片滑动
	 */
	OnTouchListener topViewsTouch = new OnTouchListener() {

		// 左右滑动时手指按下的X坐标
		private float touchDownX;
		// 左右滑动时手指松开的X坐标
		private float touchUpX;

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// 取得左右滑动时手指按下的X坐标
				touchDownX = event.getX();
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				// 取得左右滑动时手指松开的X坐标
				touchUpX = event.getX();
				// 从左往右，看前一个View
				if (touchUpX - touchDownX > INT_FLING_MIN_DISTANCE) {
					// 设置View切换的动画
					showPrevious();
					// 从右往左，看后一个View
				} else if (touchDownX - touchUpX > INT_FLING_MIN_DISTANCE) {
					// 显示前一个View
					showNext();
				}
				return true;
			}
			return false;
		}
	};

	/**
	 * 设置View切换的动画
	 */
	private void showPrevious() {

		if (topViews.getDisplayedChild() > 0) {

			// 当像右侧滑动的时候
			topViews.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			topViews.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_out));

			topViews.showPrevious();

			// 设置底部进度条显示
			changeProcess(topViews.getDisplayedChild());

		} else {
			ShowMessageUtils.show(this, R.string.msg_is_first_page);
		}
	}

	/**
	 * 显示前一个View
	 */
	private void showNext() {
		if (topViews.getDisplayedChild() < (topViews.getChildCount() - 1)) {

			// 设置View进入屏幕时候使用的动画
			topViews.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_in));
			// 设置View退出屏幕时候使用的动画
			topViews.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));

			topViews.showNext();

			// 设置底部进度条显示
			changeProcess(topViews.getDisplayedChild());

		} else {
			ShowMessageUtils.show(this, R.string.msg_is_last_page);
		}
	}

	/**
	 * 设置数据
	 */
	private void getData() {

		String jsonData = "{\"status\":0,\"total\":206,\"results\":[{\"tittle\":\"花桥地铁开通了1\",\""
				+ "tDtail\":\"花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通"
				+ "了\",\"commentN\":\"152\",\"author\":\"鬼太郎\",\"img\":\"http://thecustomizewind"
				+ "ows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png\",\"uid\":\"befdb"
				+ "292767279f887154123\"},{\"tittle\":\"花桥地铁开通了2\",\"tDtail\":\"花桥地铁开通了花桥地铁开通"
				+ "了花桥地铁开通了花桥地铁开通了花桥地铁开通了\",\"commentN\":\"152\",\"author\":\"鬼"
				+ "太郎\",\"img\":\"http://thecustomizewindows.com/wp-content/uploads/2011/11/Nic"
				+ "est-Android-Live-Wallpapers.png\",\"uid\":\"befdb292767279f887154123\"},{\"tittle\":\"花桥"
				+ "地铁开通了3\",\"tDtail\":\"花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥"
				+ "地铁开通了\",\"commentN\":\"152\",\"author\":\"鬼太郎\",\"img\":\"http://thecustomizew"
				+ "indows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png\",\"uid\":\"befd"
				+ "b292767279f887154123\"},{\"tittle\":\"花桥地铁开通了4\",\"tDtail\":\"花桥地铁开通了花"
				+ "桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了\",\"commentN\":\"152\",\"auth"
				+ "or\":\"鬼太郎\",\"img\":\"http://thecustomizewindows.com/wp-content/uploads/2011/11/Ni"
				+ "cest-Android-Live-Wallpapers.png\",\"uid\":\"befdb292767279f887154123\"},{\"tittle\":\"花桥地"
				+ "铁开通了5\",\"tDtail\":\"花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开通了花桥地铁开"
				+ "通了\",\"commentN\":\"152\",\"author\":\"鬼太郎\",\"img\":\"http://thecustomizewindows.co"
				+ "m/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png\",\"uid\":\"befdb292767"
				+ "279f887154123\"},{\"tittle\":\"花桥地铁开通了6\",\"tDtail\":\"花桥地铁开通了花桥地铁开通了花桥地"
				+ "铁开通了花桥地铁开通了花桥地铁开通了\",\"commentN\":\"152\",\"author\":\"鬼太郎\",\"img\":\"ht"
				+ "tp://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.pn"
				+ "g\",\"uid\":\"befdb292767279f887154123\"}]}";
		//
		// try {
		//
		// // 从Json数据设置论坛数据
		// postsesAll = B03v00Util.getPostsDataFromJson(jsonData);
		//
		// // 设置总数量
		// totalAll = (Integer) postsesAll.get(B03v00Util.POSTS_TOTAL_KEY);
		//
		// // 设置论坛
		// postses = (List<Posts>) postsesAll.get(B03v00Util.POSTS_DATA_KEY);
		//
		// } catch (JSONException e) {
		// e.printStackTrace();
		// ShowMessageUtils.show(this, R.string.b03v00_error_msg);
		// }
	}

	/**
	 * 设置数据数据源
	 */
	private void getAdapterData() {

		if (postses != null) {
			for (Posts posts : postses) {

				// 设置ListView Item
				Map<String, String> dataItem = new HashMap<String, String>();

				dataItem.put(B03V00PostsAdapter.from[0], posts.getTittle());
				dataItem.put(B03V00PostsAdapter.from[1], posts.getAuthor());
				dataItem.put(B03V00PostsAdapter.from[2], posts.getCommentN());

				data.add(dataItem);
			}
		}
	}

	/**
	 * 设置导航数据
	 */
	private void setNavAdapter() {

		// 清空数据源
		navListData.clear();

		// 所有类型的名称
		// String[] navNames =
		// getResources().getStringArray(R.array.v03v00_nav_level_0);
		//
		// for (int i = 0; i < navNames.length; i++) {
		// String navName = navNames[i];
		//
		// Map<String, Object> navItem = new HashMap<String, Object>();
		//
		// navItem.put(B03V00NavAdapter.p_from[0],
		// B03V00NavAdapter.nav_logo_ids[i]);
		// navItem.put(B03V00NavAdapter.p_from[1], navName);
		//
		// navListData.add(navItem);
		// }
	}

	/**
	 * 设置导航数据(第二层)
	 */
	private void setNavLevel2Adapter() {

		// 清空数据源
		navListChildData.clear();

		// 设置Item
		for (int i = 0; i < B03V00NavAdapter.level2Ids.length; i++) {

			List<Map<String, String>> childItem = new ArrayList<Map<String, String>>();

			String[] childItemNames = getResources().getStringArray(
					B03V00NavAdapter.level2Ids[2]);

			for (String string : childItemNames) {

				Map<String, String> childItemMap = new HashMap<String, String>();
				childItemMap.put(B03V00NavAdapter.from[0], string);

				childItem.add(childItemMap);
			}

			navListChildData.add(childItem);
		}
	}

	/**
	 * 设置置顶图片数据
	 */
	private void setTopViewFilterData() {

		// 清空View
		topViews.removeAllViews();
		topViewsProcess.removeAllViews();

		// 设置ViewFilter
		for (int i = 0; i < 5; i++) {

			// 设置ViewFilter
			View topItemView = getLayoutInflater().inflate(
					R.layout.b03v00_top_item, null);

			ImageView topItemImg = (ImageView) topItemView
					.findViewById(R.id.b03v00TompItemImg);
			topItemImg.setBackgroundResource(R.drawable.test_img_2);

			TextView topItemName = (TextView) topItemView
					.findViewById(R.id.b03v00TompItemName);
			topItemName.setText("花桥地铁开通了--" + i);

			// 添加到滑动View
			topViews.addView(topItemView, i);

			// 设置显示的进度条
			topViewsProcess.setWeightSum(i + 1);

			// 设置进度
			ImageView topItemProcess = new ImageView(this);

			if (i == 0) {
				topItemProcess.setBackgroundResource(R.drawable.process_line_1);
			} else {
				topItemProcess.setBackgroundResource(R.drawable.process_line_0);
			}

			LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			// itemParams.weight = 1;

			topViewsProcess.addView(topItemProcess, itemParams);
		}
	}

	/**
	 * 切花process显示
	 * 
	 * @param index
	 */
	private void changeProcess(int index) {

		for (int i = 0; i < topViewsProcess.getChildCount(); i++) {

			ImageView topItemProcess = (ImageView) topViewsProcess
					.getChildAt(i);

			if (i == index) {
				topItemProcess.setBackgroundResource(R.drawable.process_line_1);
			} else {
				topItemProcess.setBackgroundResource(R.drawable.process_line_0);
			}
		}

	}

}
