package com.xundian360.huaqiaotong.view.com;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.xundian360.huaqiaotong.R;

/**
 * 开关按钮（4.0以前）
 * @author xiaanming
 */
public class WiperSwitch extends View implements OnTouchListener{
	private Bitmap bg_on, bg_off, slipper_btn;
	/**
	 * 按下时的x和当前的x
	 */
	private float downX = 0;
	private float nowX = 0;
	
	/**
	 * 记录用户是否在滑动
	 */
	private boolean onSlip = false;
	
	/**
	 * 当前的状态
	 */
	private boolean nowStatus = true;
	
	/**
	 * 监听接口
	 */
	private OnChangedListener listener;
	
//	
//	public WiperSwitch(Context context) {
//		super(context);
//	}

	public WiperSwitch(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// 初始化
		init(context, attrs);
	}
	
	/**
	 * 初始化
	 */
	private void init(Context context, AttributeSet attrs) {
		
		TypedArray arr = context.obtainStyledAttributes(attrs,R.styleable.WiperSwitch); 
		
		// 设置自定义属性
		// 选中状态
		int bg_on_id = arr.getResourceId(R.styleable.WiperSwitch_bg_on, -1);
		
		if(bg_on_id == -1) {
			bg_on_id = R.drawable.b00v02_shangxing;
		}
		
		bg_on = BitmapFactory.decodeResource(getResources(), bg_on_id); 
		
		// 关闭状态
		int bg_off_id = arr.getResourceId(R.styleable.WiperSwitch_bg_off, -1);
		
		if(bg_off_id == -1) {
			bg_off_id = R.drawable.b00v02_xiaxing;
		}
		
	    bg_off = BitmapFactory.decodeResource(getResources(), bg_off_id);
	    
	    // 开关
		int bg_slipper_id = arr.getResourceId(R.styleable.WiperSwitch_slipper_btn, -1);
		
		if(bg_slipper_id == -1) {
			bg_slipper_id = R.drawable.b00v02_slipper;
		}
	    
	    slipper_btn = BitmapFactory.decodeResource(getResources(), bg_slipper_id);  
		
		// 设置点击事件
		setOnTouchListener(this);
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		float x = 0;
		
		if(nowX == 0) {
			if(nowStatus){
				canvas.drawBitmap(bg_on, matrix, paint);//画出打开时的背景 
			} else {
				canvas.drawBitmap(bg_off, matrix, paint);//画出关闭时的背景
			}
			
		} else {
			
			//根据nowX设置背景，开或者关状态
			if (nowX < (bg_on.getWidth()/2)){
				canvas.drawBitmap(bg_off, matrix, paint);//画出关闭时的背景
			}else{
				canvas.drawBitmap(bg_on, matrix, paint);//画出打开时的背景 
			}
		}
		
		if (onSlip) {//是否是在滑动状态,  
			if(nowX >= bg_on.getWidth())//是否划出指定范围,不能让滑块跑到外头,必须做这个判断
				x = bg_on.getWidth() - slipper_btn.getWidth()/2;//减去滑块1/2的长度
			else
				x = nowX - slipper_btn.getWidth()/2;
		}else {
			if(nowStatus){//根据当前的状态设置滑块的x值
				x = bg_on.getWidth() - slipper_btn.getWidth();
			}else{
				x = 0;
			}
		}
		
		//对滑块滑动进行异常处理，不能让滑块出界
		if (x < 0 ){
			x = 0;
		}
		else if(x > bg_on.getWidth() - slipper_btn.getWidth()){
			x = bg_on.getWidth() - slipper_btn.getWidth();
		}
		
		//画出滑块
		canvas.drawBitmap(slipper_btn, x , 0, paint); 
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:{
			if (event.getX() > bg_off.getWidth() || event.getY() > bg_off.getHeight()){
				return false;
			}else{
				onSlip = true;
				downX = event.getX();
				nowX = downX;
			}
			break;
		}
		case MotionEvent.ACTION_MOVE:{
			nowX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP:{
			onSlip = false;
			if(event.getX() >= (bg_on.getWidth()/2)){
				nowStatus = true;
				nowX = bg_on.getWidth() - slipper_btn.getWidth();
			}else{
				nowStatus = false;
				nowX = 0;
			}
			
			if(listener != null){
				listener.OnChanged(WiperSwitch.this, nowStatus);
			}
			break;
		}
		}
		//刷新界面
		invalidate();
		return true;
	}
	
	/**
	 * 为WiperSwitch设置一个监听，供外部调用的方法
	 * @param listener
	 */
	public void setOnChangedListener(OnChangedListener listener){
		this.listener = listener;
	}
	
	/**
	 * 设置滑动开关的初始状态，供外部调用
	 * @param checked
	 */
	public void setChecked(boolean checked){
		if(checked){
			nowX = bg_off.getWidth();
		}else{
			nowX = 0;
		}
		nowStatus = checked;
	}
	
	//针对不同地mode值，设置本View地大小
		 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
			 //获得父View传递给我们地测量需求
			 int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		     int heightMode = MeasureSpec.getMode(heightMeasureSpec);
			 
		     int width = 0 ;
		     int height = 0 ;
		     //对UNSPECIFIED 则抛出异常
		     if(widthMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.UNSPECIFIED)
//		         throw new RuntimeException("widthMode or heightMode cannot be UNSPECIFIED");
		    	height = bg_on.getHeight() ;
		     	width = bg_on.getWidth();
		     	
		     //精确指定
		     if(widthMode == MeasureSpec.EXACTLY){
		    	 width = 200 ;
		     }
		     //模糊指定
		     else if(widthMode == MeasureSpec.AT_MOST )
		    	 width = bg_on.getWidth(); 
		     
		      //精确指定
		     if(heightMode == MeasureSpec.EXACTLY){
		    	 height = 100 ;
		     }
		     //模糊指定
		     else if(heightMode == MeasureSpec.AT_MOST )
		    	 height = bg_on.getHeight() ;
		     
		     setMeasuredDimension(width , height) ;
		 }

	
    /**
     * 回调接口
     * @author len
     *
     */
	public interface OnChangedListener {
		public void OnChanged(WiperSwitch wiperSwitch, boolean checkState);
	}

}
