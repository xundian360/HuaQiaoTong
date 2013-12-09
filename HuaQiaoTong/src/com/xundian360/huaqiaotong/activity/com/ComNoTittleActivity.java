/**
 * 
 */
package com.xundian360.huaqiaotong.activity.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 没有Tittle Activity 父类
 * @author   tengteng
 * @time     上午11:54:28
 * @version  1.0
 */
public class ComNoTittleActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//		              WindowManager.LayoutParams. FLAG_FULLSCREEN);
	}

}
