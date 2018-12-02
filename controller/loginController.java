package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.User;

public class loginController {
	
	public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params)
	{
		InputStream is = null;
		String json = "";
		JSONObject jObj = null;
		
		try
		{
			if(method == "POST")
			{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				
			}
			else if(method == "GET")
				{
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String paramString = URLEncodedUtils.format(params, "utf8");
					url += "?" + paramString;			
					HttpGet httpGet = new HttpGet(url);					
					HttpResponse httpResponse = httpClient.execute(httpGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					
				}
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				is, "iso-8859-1"),8);	
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine())!= null)
			{
				sb.append(line + "\n");
			}
			
			is.close();
			json = sb.toString();
			jObj = new JSONObject(json);
		
		}

		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		
		return jObj;	
	}
	
	public String doLogin(User user) throws Exception
	{
		String level ="";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("selectFn","login"));
		params.add(new BasicNameValuePair("username",user.getUsername()));
		params.add(new BasicNameValuePair("password",user.getPassword()));
	
		
		String strUrl = "http://127.0.0.1:8888/petRescue/login.php";
		JSONObject jObj = makeHttpRequest(strUrl,"POST",params);
		level = jObj.optString("level");
		return level;
	}
	
	public String addUser(User user) throws Exception
	{
		String success = "";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("selectFn","register"));
		params.add(new BasicNameValuePair("username",user.getUsername()));
		params.add(new BasicNameValuePair("password",user.getPassword()));
		params.add(new BasicNameValuePair("gender",user.getGender()));
		params.add(new BasicNameValuePair("mobile",user.getMobile()));
	
		
		String strUrl = "http://127.0.0.1:8888/petRescue/login.php";
		JSONObject jObj = makeHttpRequest(strUrl,"POST",params);
		success = jObj.optString("success");
		return success;
	}
}
