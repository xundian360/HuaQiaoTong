/**
 * 
 */
package com.xundian360.huaqiaotong.activity.b04;

import java.util.HashMap;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.activity.com.ComNoTittleActivity;
import com.xundian360.huaqiaotong.modle.com.UserModle;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;
import com.xundian360.huaqiaotong.view.com.SimpleListDialog;

/**
 * 登陆
 * @author  Administrator
 * @date      2013年10月23日
 * @version 1.0
 */
public class B04V00Activity extends ComNoTittleActivity implements PlatformActionListener, Callback {
	
	// 用户名
	EditText userName;
	// 密码
	EditText userPass;
	
	// 注册
	ImageView signBtn;
	// 其他方式登录
	ImageView loginOtherBtn;
	
	// 用户存储类
	UserModle userModle;
	
	SimpleListDialog loginSelectDialog;
	
	String[] loginWay = {"QQ", "新浪微博"};
	String loginWayTittle = "其他方式登录";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.b04v00);
		
		// 初始化数据
		initData();
		
		// 初始化组件
		initModule();
	}
	
	@Override
	protected void onStart() {
		userModle.read();
		super.onStart();
	}
	
	/**
	 *  初始化数据
	 */
	private void initData(){
		userModle = new UserModle(this);
	}
	
	/**
	 *  初始化组件
	 */
	private void initModule(){
		
		userName = (EditText) findViewById(R.id.b04v00UserName);
		userPass = (EditText) findViewById(R.id.b04v00UserPas);
		userPass.setOnEditorActionListener(passwordActionListener);
		
//		signBtn = (ImageView) findViewById(R.id.b04v00SignBtn);
//		signBtn.setOnClickListener(signBtnClick);
//		
//		loginOtherBtn = (ImageView) findViewById(R.id.b04v00OtherLoginBtn);
//		loginOtherBtn.setOnClickListener(loginOtherBtnClick);
	}
	
	/**
	 * 注册
	 */
	OnClickListener signBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			CommonUtil.startActivityForResult(B04V00Activity.this, B04V01Activity.class, 100);
		}
	};
	
	/**
	 * 其他方式登录
	 */
	OnClickListener loginOtherBtnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			ShowMessageUtils.show(B04V00Activity.this, "其他方式登录");
			
			// 非空
			if(loginSelectDialog == null) {
				loginSelectDialog = new SimpleListDialog(B04V00Activity.this, 
						onLoginSelect, 
						loginWayTittle, 
						loginWay);
			}
			
			loginSelectDialog.show();
		}
	};
	
	/**
	 * 登录方式选择
	 */
	DialogInterface.OnClickListener onLoginSelect = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			
			String pingtai = null;

			switch (which) {
			case 0:
				// QQ分享
				pingtai = QZone.NAME; 
				
				break;
			case 1:
				//新浪微博分享
				pingtai = SinaWeibo.NAME; 
				
				break;

			default:
				break;
			}
			
			if (pingtai != null) {
				//初始化平台
				Platform pf = ShareSDK.getPlatform(B04V00Activity.this, pingtai);
				//设置监听
				pf.setPlatformActionListener(B04V00Activity.this);
				//获取登陆用户的信息，如果没有授权，会先授权，然后获取用户信息
				pf.showUser(null);
			}
			
		}
	};
	
	/**
	 * 键盘按键监听
	 */
	EditText.OnEditorActionListener passwordActionListener = new EditText.OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	        // 完成按钮
			if (actionId == EditorInfo.IME_ACTION_DONE) {
	        	// 登陆
	        	login();
	            return true;    
	        }  
	        return false;  
		}
	};
	
	/**
	 * 登陆
	 */
	private void login() {
    	
		String userNameText = userName.getText().toString();
		String userPassText = userPass.getText().toString();
		
		// 用户名检验
    	if(StringUtils.isBlank(userNameText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_name_null);
    		return;
    	}
	    
    	// 密码校验
    	else if(StringUtils.isBlank(userPassText)) {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_pass_null);
    		return;
    	}
    	
    	// 判断是否登录成功
    	else if(userNameText.equals(userModle.user.getLoginName())) {
    		// 个人中心
    		CommonUtil.startActivityForResult(B04V00Activity.this, B04V03Activity.class, 100);
    	} else {
    		ShowMessageUtils.show(this, R.string.b04v01_msg_login_error);
    	}
	}
	
	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;

	//设置监听http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
	//监听是子线程，不能Toast，要用handler处理，不要犯这么二的错误
	@Override
	public void onCancel(Platform platform, int action) {
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
		/** arg2是返回的数据，例如showUser(null),返回用户信息，对其解析就行
		*   http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
		*   1、不懂如何解析hashMap的，可以上网搜索一下
		*   2、可以参考官网例子中的GetInforPage这个类解析用户信息
		*   3、相关的key-value,可以看看对应的开放平台的api
		*     如新浪的：http://open.weibo.com/wiki/2/users/show
		*     腾讯微博：http://wiki.open.t.qq.com/index.php/API%E6%96%87%E6%A1%A3/%E5%B8%90%E6%88%B7%E6%8E%A5%E5%8F%A3/%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8D%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E7%9A%84%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99
		*/
		
		//把hashMap转换成json
		//JsonUtils ju = new JsonUtils();
		//String json = ju.fromHashMap(res);
		
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg, this);
	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		t.printStackTrace();

		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg, this);		
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch(msg.what) {
		case MSG_TOAST: {
			String text = String.valueOf(msg.obj);
			Toast.makeText(B04V00Activity.this, text, Toast.LENGTH_SHORT).show();
		}
		break;
		case MSG_ACTION_CCALLBACK: {
			switch (msg.arg1) {
				case 1: { // 成功
					ShowMessageUtils.show(B04V00Activity.this, "授权成功");

					//授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
					//ShareSDK只保存以下这几个通用值
					Platform pf = ShareSDK.getPlatform(B04V00Activity.this, SinaWeibo.NAME);
					Log.e("sharesdk use_id", pf.getDb().getUserId()); //获取用户id
					Log.e("sharesdk use_name", pf.getDb().getUserName());//获取用户名称
					Log.e("sharesdk use_icon", pf.getDb().getUserIcon());//获取用户头像
					//pf.author()这个方法每一次都会调用授权，出现授权界面
					//如果要删除授权信息，重新授权
					//pf.getDb().removeAccount();
					//调用后，用户就得重新授权，否则下一次就不用授权
					
					
				}
				break;
				case 2: { // 失败
					String expName = msg.obj.getClass().getSimpleName();
					if ("WechatClientNotExistException".equals(expName)
							|| "WechatTimelineNotSupportedException".equals(expName)) {
						ShowMessageUtils.show(B04V00Activity.this, getString(R.string.wechat_client_inavailable));
					}
					else if ("GooglePlusClientNotExistException".equals(expName)) {
						ShowMessageUtils.show(B04V00Activity.this, getString(R.string.google_plus_client_inavailable));
					}
					else if ("QQClientNotExistException".equals(expName)) {
						ShowMessageUtils.show(B04V00Activity.this, getString(R.string.qq_client_inavailable));
					}
					else {
						ShowMessageUtils.show(B04V00Activity.this, "授权失败");
					}
				}
				break;
				case 3: { // 取消
					ShowMessageUtils.show(B04V00Activity.this, "取消授权");
				}
				break;
			}
		}
		break;
		case MSG_CANCEL_NOTIFY: {
			NotificationManager nm = (NotificationManager) msg.obj;
			if (nm != null) {
				nm.cancel(msg.arg1);
			}
		}
		break;
	}
	return false;
	}
}
