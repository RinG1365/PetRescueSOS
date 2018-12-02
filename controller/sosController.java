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

import model.SOS;
import model.Station;

public class sosController {
	
	public JSONArray jsnArr;
	public JSONArray getJsnArr() {
		return jsnArr;
	}

	public void setJsnArr(JSONArray jsnArr) {
		this.jsnArr = jsnArr;
	}
	
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
		catch(JSONException e)
		{
			try 
			{
					JSONArray jsnArr = new JSONArray(json);
					setJsnArr(jsnArr);
				
			}
			catch(JSONException e1)
			{
				e1.printStackTrace();
			}
		}

		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		
		return jObj;	
	}
	
	public JSONArray getStation() throws Exception
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("selectFn","getAllStation"));
		
		String strUrl = "http://127.0.0.1:8888/petRescue/station.php";
		JSONObject jObj = makeHttpRequest(strUrl,"POST",params);
		jsnArr = getJsnArr();
		return jsnArr;
	}
	
	public JSONArray getRescuer() throws Exception
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("selectFn","getAllRescuer"));
		
		String strUrl = "http://127.0.0.1:8888/petRescue/rescuer.php";
		JSONObject jObj = makeHttpRequest(strUrl,"POST",params);
		jsnArr = getJsnArr();
		return jsnArr;
	}
	
	public String addSOS(SOS sos) throws Exception
	{
		String success = "";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("selectFn","addSOS"));
		params.add(new BasicNameValuePair("sosDate",sos.getSOSDate()));
		params.add(new BasicNameValuePair("sosTime",sos.getSOSTime()));
		params.add(new BasicNameValuePair("stationID",sos.getSid()));
		params.add(new BasicNameValuePair("sender",sos.getSender()));
		params.add(new BasicNameValuePair("pType",sos.getpType()));
		params.add(new BasicNameValuePair("pQuan",sos.getpQuan()));
		params.add(new BasicNameValuePair("rescuerID",sos.getRid()));
		params.add(new BasicNameValuePair("status",sos.getStatus()));
		
		
		String strUrl = "http://127.0.0.1:8888/petRescue/sos.php";
		JSONObject jObj = makeHttpRequest(strUrl,"POST",params);
		success = jObj.optString("success");
		return success;
		
	}
	

	

}
