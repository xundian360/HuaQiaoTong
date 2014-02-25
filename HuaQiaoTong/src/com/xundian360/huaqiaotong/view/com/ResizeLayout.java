/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 键盘监听
 * 
 * @author TengTeng
 * @date 2013-10-8
 * @version 1.0
 */
public class ResizeLayout extends RelativeLayout {

	private IsOpenInputModle mListener;
	private boolean isOpen = false;
	private int oh;

	public ResizeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ResizeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ResizeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public void setOpenInputModle(IsOpenInputModle l) {
		mListener = l;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		if ((oldw + oldh) == 0 || Math.abs(h - oldh) < 100) {
			return;
		}

		// 打开键盘
		if (!isOpen && oldh > h) {
			isOpen = true;
			oh = oldh;
			if (null != mListener) {
				mListener.isOpen(true);
			}
		}

		// 隐藏键盘
		else if (h == oh || Math.abs(h - oh) < 60) {
			isOpen = false;
			if (null != mListener) {
				mListener.isOpen(false);
			}
		}
	}

	public interface IsOpenInputModle {
		void isOpen(boolean isOpen);
	};
}