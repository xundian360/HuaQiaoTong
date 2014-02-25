package com.xundian360.huaqiaotong.view.com;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.widget.DatePicker;

import com.xundian360.huaqiaotong.util.StringUtils;

/**
 * 时间选择Dialog
 * 
 * @author tengteng
 * @date 2013-12-24
 * @version 1.0
 */
public class SimpleYyMmDdSelectDialog {

	DatePickerDialog dialog;

	private SimpleYyMmDdSelectCallBackInterface callback;

	public SimpleYyMmDdSelectDialog(final Context context,
			SimpleYyMmDdSelectCallBackInterface callback) {

		this.callback = callback;

		// 拿到系统的日期
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		dialog = new DatePickerDialog(context, enter, year, month, day);
		// dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
	}

	public void show() {

		dialog.show();
	}

	public boolean isShowing() {

		return dialog.isShowing();
	}

	/*
	 * 确认按钮
	 */
	private OnDateSetListener enter = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			Calendar calendarTemp = new GregorianCalendar(year, monthOfYear,
					dayOfMonth);

			callback.callbackDate(StringUtils.getFormatTime1(calendarTemp
					.getTime()));
		}

	};

	public interface SimpleYyMmDdSelectCallBackInterface {
		public void callbackDate(String date);
	}

}
