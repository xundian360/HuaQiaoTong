package com.xundian360.huaqiaotong.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * @author miaoxing
 */
public class StringUtils {

	/**
	 * The empty String <code>""</code>.
	 * 
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	/**
	 * Represents a failed index search.
	 * 
	 * @since 2.1
	 */
	public static final int INDEX_NOT_FOUND = -1;

	public StringUtils() {
		super();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	// Equals
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}
	
	@SuppressWarnings("finally")
	public static boolean isFloat(String str) {
		
		boolean isFloat = true;
		
		try{
		
			Float.parseFloat(str);
			
		} catch(Exception e) {
			
			isFloat = false;
			
		} finally {
			
			return isFloat;
		}
		
	}

	public static String findGeoPoint(String text) {
		String regex = "(-?\\d+\\.\\d+,-?\\d+\\.\\d+)";
		String s = "";
		for (Character c : text.toCharArray()) {
			if (Character.isDigit(c) || '.' == c || ',' == c || '-' == c) {
				s += c;
			} else {
				if (Pattern.matches(regex, s)) {
					break;
				} else {
					s = "";
				}
			}
		}
		if (!Pattern.matches(regex, s))
			s = "";
		return s;
	}

	public static boolean hasGeoPoint(String text) {

		return StringUtils.isNotEmpty(findGeoPoint(text));
	}

	/*
	 * 时间转换为‘yyyy-MM-dd’格式的字符串
	 */
	public static String getFormatTime1(Date time) {
		SimpleDateFormat simpleDateFormatUsed = new SimpleDateFormat(
				"yyyy-MM-dd");

		String dateString = simpleDateFormatUsed.format(time);

		return dateString;

	}
	
	/*
	 * 时间转换为‘yyyy-MM-dd hh:mm’格式的字符串
	 */
	public static String getFormatTime2(Date time) {
		SimpleDateFormat simpleDateFormatUsed = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");

		String dateString = simpleDateFormatUsed.format(time);

		return dateString;

	}

	/*
	 * ‘yyyy-MM-dd’格式的字符串转化为时间
	 */
	public static Date getDataFromString(String timeString) {
		SimpleDateFormat simpleDateFormatUsed = new SimpleDateFormat(
				"yyyy-MM-dd");

		Date date = null;
		try {
			date = simpleDateFormatUsed.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/*
	 * 正则表达式校验
	 */
	public static boolean regexChk(String reg, String string) {

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string.toLowerCase());

		return matcher.matches();
	}

	/*
	 * float类型的数字转化为 XXX.00 格式的钱币金额表示
	 */
	public static String formatFloatToString1(float number) {

		DecimalFormat dcmFmt = new DecimalFormat("0.00");
		return dcmFmt.format(number);
	}
	
	/*
	 * double类型的数字转化为 XXX.00 格式的钱币金额表示
	 */
	public static String formatDoubleToString1(double number) {

		DecimalFormat dcmFmt = new DecimalFormat("0.00");
		return dcmFmt.format(number);
	}
	
	/*
	 * double类型的数字转化为 XXX.0 格式的钱币金额表示
	 */
	public static String formatDoubleToString(double number) {

		DecimalFormat dcmFmt = new DecimalFormat("0.0");
		return dcmFmt.format(number);
	}

	/**
	 * 计算月供(等额本息)
	 * 
	 * @param rate
	 *            年利率 年利率除以12就是月利率
	 * @param term
	 *            贷款期数，单位月
	 * @param financeAmount
	 *            贷款金额
	 * @return
	 */
	public static float PMT(float rate, int term, float financeAmount) {
		float v = (1 + (rate / 12));
		float t = (-(term / 12) * 12);
		if (t == -12.0) {
			// 一年按十三个月计算
			t = -13;
		}
		float result = (float) ((financeAmount * (rate / 12)) / (1 - Math.pow(
				v, t)));
		return result;
	}

	/**
	 * 计算月供(智慧式)
	 * 
	 * @param rate
	 *            年利率 年利率除以12就是月利率
	 * @param term
	 *            贷款期数，单位月
	 * @param financeAmount
	 *            贷款金额
	 * @param finalPaymentMoney
	 *            尾款
	 * @return
	 */
	public static float PMT(float rate, int term, float financeAmount,
			float finalPaymentMoney) {

		float v = rate / 12;

		float resultTemp = (float) ((v * finalPaymentMoney) / (Math.pow(
				(1 + v), term) - 1));

		float result = PMT(rate, term, financeAmount) - resultTemp;

		return result;
	}

	/**
	 * 计算月供(等额本金)
	 * 
	 * @param rate
	 *            年利率 年利率除以12就是月利率
	 * @param term
	 *            贷款期数，单位月
	 * @param financeAmount
	 *            贷款金额
	 * @param finalPaymentMoney
	 *            尾款
	 * @return
	 */
	public static float[] PMT2(float rate, int term, float financeAmount) {

		float[] result = new float[term];

		// 月还款金额
		double pay = (double) financeAmount / term;
		// 月利率
		double v = rate / ((term / 12) * 12);
		double[] interest = new double[term];

		// 每月的利息
		for (int i = 0; i < interest.length; i++) {
			interest[i] = (financeAmount - pay * i) * v;
		}
		for (int i = 0; i < interest.length; i++) {
			result[i] = (float) (pay + interest[i]);

		}

		return result;
	}
	
	/**
	 * 重写Integer.parseInt方法
	 * @param value         转换值
	 * @param defaultValue  转换失败，默认值
	 */
	public static int paseInt(String value, int defaultValue) {
		
		int valueInt = defaultValue;
		
		if (StringUtils.isNotBlank(value)) {
			valueInt = Integer.parseInt(value);
		}
		
		return valueInt;
	}
	
	/**
	 * 重写Double.parseDouble方法
	 * @param value         转换值
	 * @param defaultValue  转换失败，默认值
	 */
	public static double paseDouble(String value, int defaultValue) {
		
		double valueDouble = defaultValue;
		
		if (StringUtils.isNotBlank(value)) {
			valueDouble = Double.parseDouble(value);
		}
		
		return valueDouble;
	}
	
	/**
	 * 重写Double.parseFloat方法
	 * @param value         转换值
	 * @param defaultValue  转换失败，默认值
	 */
	public static float paseFloat(String value, int defaultValue) {
		
		float valueDouble = defaultValue;
		
		if (StringUtils.isNotBlank(value)) {
			valueDouble = Float.parseFloat(value);
		}
		
		return valueDouble;
	}
	
	/** 
     * 生成唯一字符串  
     * @param length 需要长度 
     * @param symbol 是否允许出现特殊字符 -- !@#$%^&*() 
     * @return 
     */  
    public static String getUniqueString(int length, boolean symbol) {  
        Random ran = new Random();  
        int num = ran.nextInt(61);  
        String returnString = "";  
        String str = "";  
        for (int i = 0; i < length;) {  
            if (symbol)  
                num = ran.nextInt(70);  
            else  
                num = ran.nextInt(61);  
            str = strArray[num];  
            if (!(returnString.indexOf(str) >= 0)) {  
                returnString += str;  
                i++;  
            }  
        }  
        return returnString;  
    } 
    
    /** 
     * 给生成唯一字符串使用 
     */  
    private static String[] strArray = new String[] { "a", "b", "c", "d", "e",  
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",  
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",  
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",  
            "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4",  
            "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "(",  
            ")" }; 
}
