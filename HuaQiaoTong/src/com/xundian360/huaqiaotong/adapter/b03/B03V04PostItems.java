/**
 * 
 */
package com.xundian360.huaqiaotong.adapter.b03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xundian360.huaqiaotong.R;
import com.xundian360.huaqiaotong.modle.b03.Posts;

import android.content.Context;
import android.widget.SimpleAdapter;

/**
 * @author  TeneTeng
 * @date      2014-2-25
 * @version 1.0
 */
public class B03V04PostItems extends SimpleAdapter {
	
	public static String[] fromKey = {"b03v04UserLogo", "b03v04UserName",
		"b03v04ItemImg", "b03v04ItemTittle", "b03v04ItemComNum", "b03v04ItemComTime"};
	
	public static int[] idKey = {R.id.b03v04UserLogo, R.id.b03v04UserName, R.id.b03v04ItemImg,
		R.id.b03v04ItemTittle, R.id.b03v04ItemComNum, R.id.b03v04ItemComTime};
	
	List<Posts> posts = new ArrayList<Posts>();

	public B03V04PostItems(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}
	
	/**
	 * 设置帖子
	 * @param posts
	 */
	public void setPostsList(List<Posts> posts) {
		this.posts = posts;
	}

}
