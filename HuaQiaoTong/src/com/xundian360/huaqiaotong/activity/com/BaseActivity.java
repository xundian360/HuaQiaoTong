package com.xundian360.huaqiaotong.activity.com;

import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.xundian360.huaqiaotong.R;

public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;

	// protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
		// WindowManager.LayoutParams. FLAG_FULLSCREEN);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		// if (savedInstanceState == null) {
		// FragmentTransaction t =
		// this.getSupportFragmentManager().beginTransaction();
		// mFrag = new ListFragment();
		// t.replace(R.id.menu_frame, mFrag);
		// t.commit();
		// } else {
		// mFrag =
		// (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		// }

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setSecondaryShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

	}
}
