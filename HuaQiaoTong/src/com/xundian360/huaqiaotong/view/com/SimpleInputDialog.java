package com.xundian360.huaqiaotong.view.com;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.xundian360.huaqiaotong.R;

public class SimpleInputDialog {
	
	private AlertDialog.Builder builder;
	private AlertDialog dialog; 
	
	EditText textView;

	public SimpleInputDialog(final Context context, final DialogInterface.OnClickListener onClickListener) {
		
		builder = new AlertDialog.Builder(context); 

		View dialogView = LayoutInflater.from(context).inflate(R.layout.input_dialog_layout, null);
		textView = (EditText)dialogView.findViewById(R.id.common_input_text);
		
		builder.setPositiveButton(R.string.common_btn_enter, onClickListener);
		builder.setNegativeButton(R.string.common_btn_cancel, null);
		
		builder.setView(dialogView);
		dialog = builder.create();
	}
	
	public String getMsg() {
		return textView.getText().toString();
	}

	public void show() {
		if(!dialog.isShowing()) {
			dialog.show();
		}
	}
	
	public void setMsg(String text) {
		
		textView.setText(text);
	}
}
