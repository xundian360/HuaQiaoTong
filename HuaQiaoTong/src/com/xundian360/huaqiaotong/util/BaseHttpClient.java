package com.xundian360.huaqiaotong.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.RequestParams;
/**
 * Http访问工具类
 * @author miaoxing
 */
public class BaseHttpClient {
	
	/**
	 * 普通Get请求
	 * @param urlString
	 * @param params
	 * @return
	 */
	static public String doGetRequest(String urlString, HashMap<String, String> params) {
		HttpClient httpClient = getHttpClient();
		
		if (params != null && !params.isEmpty()) {
        	StringBuffer buf = new StringBuffer("?");
        	for(String key : params.keySet()){
        		 buf.append("&").append(key).append("=").append(params.get(key));
        	}
        	buf.deleteCharAt(1);       	
        	urlString += buf.toString();      	
		}
		Log.d("BaseAuthenicationHttpClient", "GET > " + urlString);        
		
		try {  
			HttpGet request = new HttpGet(urlString);
            HttpResponse response = httpClient.execute(request);  
            if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){  
                String str = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);  
                return str;
            }   
        } catch (ClientProtocolException e) {  
            e.printStackTrace();      
        } catch (IOException e) {
            e.printStackTrace();  
        }
		return null;
	}
	
	/**
	 * 普通Post请求
	 * @param urlString
	 * @param params
	 * @return
	 */
	static public String doPostRequest(String urlString, Map<String, String> params) {
		HttpClient httpClient = getHttpClient();
		HttpPost request = new HttpPost(urlString);
		
		Log.d("BaseAuthenicationHttpClient", "POST > " + urlString);      
		
		try {
			if( null != params && !params.isEmpty() ) {
				request.setEntity(new UrlEncodedFormEntity(convertMapToNameValuePair(params), HTTP.UTF_8));
			}
			HttpResponse response = httpClient.execute(request);  
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
                String str = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);  
                return str;
            } 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 带文件上传的Post请求
	 * @param urlString   请求URL
	 * @param params      参数
	 * @param fileKeys   上传文件Key
	 * @param filePaths   上传文件路径 
	 * @param callBack    上传回调
	 * @return
	 */
	public static void doPostRequestWithFile(String urlString, 
			Map<String, String> params, String[] fileKeys, 
			String[] filePaths, RequestCallBack<String> callBack) {
		
		// 设置请求参数
		RequestParams rParams = new RequestParams();
		rParams.addBodyParameter(convertMapToNameValuePair(params));
		// 添加上传文件
		for (int i = 0; i < fileKeys.length; i++) {
			rParams.addBodyParameter(fileKeys[i], new File(filePaths[i]));
		}
		
		// 发送HTTP请求
		HttpUtils http = new HttpUtils();
		// 带上传回调
		http.send(HttpRequest.HttpMethod.POST, urlString, rParams, callBack);
	}
	
	static public List<NameValuePair> convertMapToNameValuePair(Map<String, String> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for( String key : params.keySet() ) {
			pairs.add(new BasicNameValuePair(key, params.get(key)));
		}
		return pairs;
	}
	
	static public HttpClient getHttpClient() {
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used. 
		int timeoutConnection = 8000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT) 
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 8000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		HttpClient httpClient = new DefaultHttpClient(httpParameters);

		return httpClient;
	}
}
