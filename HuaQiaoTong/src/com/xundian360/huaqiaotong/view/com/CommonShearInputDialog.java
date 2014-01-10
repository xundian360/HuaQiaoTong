/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import cn.sharesdk.framework.PlatformActionListener;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.util.CommonUtil;
import com.xundian360.huaqiaotong.util.ShearUtil;
import com.xundian360.huaqiaotong.util.ShowMessageUtils;
import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 分享内容输入Dialog
 * 
 * @author Administrator
 * @date 2013年10月22日
 * @version 1.0
 */
public class CommonShearInputDialog extends BottomDialog {
	
	public static final int SHEAR_WEIXIN_KEY = 1;
	public static final int SHEAR_PENGYOU_KEY = 2;
	public static final int SHEAR_WEIBO_KEY = 3;
	
	// 分享最大字节数
	private int shear_text_max_size = 100;

	Context context;
	View view;
	
	PlatformActionListener paListener;
	
	EditText shearMsg;
	Button clearBtn;
	Button shearBtn;
	
	// 分享标题
	String shearTittle;
	// 分享标题
	String shearMsgText;
	// 分享图片路径
	String shearImgPath;
	// 分享类型
	int shearKey = 0;

	public CommonShearInputDialog(Context context, int shearKey, 
			String shearTittle, String shearMsgText, String shearImgPath, PlatformActionListener paListener) {
		super(context);
		this.context = context;
		this.shearKey = shearKey;
		this.shearTittle = shearTittle;
		this.shearMsgText = shearMsgText;
		this.shearImgPath = shearImgPath;
		this.paListener = paListener;
		
		// 初始化视图
		initView();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		
		view = LayoutInflater.from(context).inflate(R.layout.common_shear_dialog_layout, null);
		
		shearMsg = (EditText) view.findViewById(R.id.commonShearMsg);
		shearMsg.setText(shearMsgText);
		
		clearBtn =  (Button) view.findViewById(R.id.commonShearClearBtn);
		clearBtn.setOnClickListener(clearBtnClick);
		
		shearBtn =  (Button) view.findViewById(R.id.commonShearBtn);
		shearBtn.setOnClickListener(shearBtnClick);
		
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		
		// 添加View到视图
		dialog.setContentView(view, 
				new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	
	/**
	 * 清空按钮
	 */
	android.view.View.OnClickListener clearBtnClick = new android.view.View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			shearMsg.setText("");
		}
	};
	
	/**
	 * 分享按钮
	 */
	android.view.View.OnClickListener shearBtnClick = new android.view.View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			String shearMagText = shearMsg.getText().toString();
			
			// 分享内容不能为空
			if(StringUtils.isNotBlank(shearMagText)) {
				
				// 分享内容不能太长
				if(shearMagText.length() > shear_text_max_size) {
					ShowMessageUtils.show(context, R.string.common_shear_msg_to_long);
				} else {
					
					// 分享方式选择
					switch (shearKey) {
					case SHEAR_WEIXIN_KEY:
						
						break;
					case SHEAR_PENGYOU_KEY:
						
						break;
					case SHEAR_WEIBO_KEY:
						
						// 分享到新浪微博
						ShearUtil.shearToSinaWeiboWithURL(context, shearMagText, shearImgPath, paListener);
						break;

					default:
						break;
					}
				}
			} else {
				ShowMessageUtils.show(context, R.string.common_shear_msg_not_empty);
			}
			
			// 取消Dialog显示
			dismiss();
		}
	};
	
	public void show() {
		super.show();
		
		// 打开键盘
		CommonUtil.showInput(context);
	};
	
}
