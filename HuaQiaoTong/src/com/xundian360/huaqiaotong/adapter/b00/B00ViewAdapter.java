package com.xundian360.huaqiaotong.adapter.b00;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xundian360.huaqiaotong.view.b00.B00LeftView;
import com.xundian360.huaqiaotong.view.b00.B00CenterView;
import com.xundian360.huaqiaotong.view.b00.B00RightView;

public class B00ViewAdapter extends BaseAdapter {

        public static final int VIEW1 = 0;
        public static final int VIEW2 = 1;
        public static final int VIEW3 = 2;
        private static final int VIEW_MAX_COUNT = VIEW3 + 1;

    	// 左边视图
        B00LeftView leftView;
    	// 中间视图
        B00CenterView centerView;
    	// 右边视图
    	B00RightView rightView;
    	
    	Context context;

    public B00ViewAdapter(Context context) {
    	
    	this.context = context;
    	
		leftView = new B00LeftView(context);
		centerView = new B00CenterView(context);
		rightView = new B00RightView(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int view = getItemViewType(position);
        if (convertView == null) {
            switch (view) {
//                case VIEW1:
//                    convertView = leftView.get();
//                    break;
//                case VIEW2:
//                    convertView = centerView.get();
//                    break;
//                case VIEW3:
//                    convertView = rightView.get();
//                    break;
		      case VIEW1:
		          convertView = leftView.get();
		          break;
		      case VIEW2:
		          convertView = centerView.get();
		          break;
		      case VIEW3:
		          convertView = rightView.get();
		          break;
            }
        }
        
        return convertView;
    }

}
