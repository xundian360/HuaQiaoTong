/**
 * 
 */
package com.xundian360.huaqiaotong.view.com;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 显示全部内容的ListView（没有滚动条）
 * @author    LiZhenteng
 * @date      2013年10月14日
 * @version   1.0
 */
public class AllShowListView extends ListView {

	public AllShowListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public AllShowListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public AllShowListView(Context context) {
		super(context);
	}
	
	 /** 
     * 设置不滚动 
     */  
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    } 

}
