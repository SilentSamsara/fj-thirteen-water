package secondpairing;
import java.io.BufferedReader;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class URLDemo
{
	public static String sendGet(String urlAddress,int x) {
		try {
			HttpURLConnection urlConnection=null;
			URL url=new URL(urlAddress);
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(5000);
			urlConnection.setDoInput(true);
			if(x==1)
			{
				urlConnection.setRequestProperty("Content-Type", "application/json");
				
			}else if(x==2)
				urlConnection.setRequestProperty("X-Auth-Token", Token);
				
			urlConnection.setUseCaches(false);
			urlConnection.connect();
			
			BufferedReader bf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String line=bf.readLine();
			return line;
		}catch (Exception e) {
			System.out.println("sendGet error!");
		}
		return null;
	}
	static String Name="X-Auth-Token";
	public static int id;
	public static String Token;
	public static String MyName="SilentSamsara";
	public static int Location;
	public static int MyScore;
	public static int TotalLocation;
	
	 public static String sendPost(String URL,JSONObject json,int x) {
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		post.setHeader("Content-Type", "application/json");
		if(Token!=null)
			post.addHeader(Name, Token);
		String result = "";
		try {
			if(x ==1)
			{
				StringEntity s = new StringEntity(json.toString(), "utf-8");
				s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
				post.setEntity(s);
			}
		    HttpResponse httpResponse = client.execute(post);
		    InputStream inStream = httpResponse.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
		    StringBuilder strber = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null)
		        strber.append(line);
		    inStream.close();
		    result = strber.toString();
		    System.out.println(result);
		} catch (Exception e) {
		    System.out.println("请求异常");
		}
		    return result;
		}
	 public static boolean register(URLDemo Demo,String username,String password,String student_number,String student_password) {
		 /*注册*/
		 JSONObject json=new JSONObject();
		 json.put("username", username);
		 json.put("password", password);
		 json.put("student_number", student_number);
		 json.put("student_password",student_password );
		 JSONObject token=JSONObject.parseObject(URLDemo.sendPost("http://api.revth.com/auth/register2", json,1));
		int mark=(JSONObject.parseObject(token.toString())).getIntValue("status");
		if(mark==0)
			return true;
		else
			return false;
	 }
	 public static boolean login(URLDemo Demo,String username,String password) {
		/*登录*/
		JSONObject json=new JSONObject();
		MyName=username;
		json.put("username", username);
		json.put("password", password);
		JSONObject token=JSONObject.parseObject(URLDemo.sendPost("http://api.revth.com/auth/login", json,1));//登录
		try {
			String Token2=(JSONObject.parseObject(token.get("data").toString())).getString("token");
			int id2=(JSONObject.parseObject(token.get("data").toString())).getIntValue("user_id");
			URLDemo.id=id2;
			URLDemo.Token=Token2;
		}catch (Exception e) {
			return true;
		}
		
		return false;
	 }
	 public static void logout(URLDemo Demo) {//注销
		 URLDemo.sendPost("http://api.revth.com/auth/logout", null, 0);
	 }
	 public static String rank(URLDemo Demo) {
		 /*获取排行榜*/
			String back=URLDemo.sendGet("http://api.revth.com/rank",0);
			JSONArray pai=JSON.parseArray(back);
			back=new String();
			TotalLocation=pai.size();
			if(TotalLocation>0)
			{
				for(int i=0;i<TotalLocation;i++)
				{
					String name=pai.getJSONObject(i).getString("name");
					if(name.equals(MyName))
					{
						Location=i+1;
						MyScore=pai.getJSONObject(i).getIntValue("score");
					}
					back=back+"第"+(i+1)+"名\n账号:"+name+"\n分数:"+pai.getJSONObject(i).getString("score")+"\n\n";
				}
			}
			back=back+"总共:"+TotalLocation+"人\n"+MyName+":第"+Location+"名\n您的分数:"+MyScore;
			return back;
	 }
	 public static String history(URLDemo Demo,String play_id,int page) {
		 /*历史战局*/
			String back=URLDemo.sendGet("http://api.revth.com/history?player_id="+play_id+"&limit=500&page="+page,2);
			JSONObject jsonObject = new JSONObject().parseObject(back);
			JSONArray pai=JSONArray.parseArray(jsonObject.getString("data").toString());
			back=new String();
			for(int i=0;i<pai.size();i++)
			{
				back=back+"分数:"+pai.getJSONObject(i).getIntValue("score")+"\n战局id:"+pai.getJSONObject(i).getIntValue("id")+"\n\n";
			}
			return back;
	 }
	 public static String  historyid(URLDemo Demo,String id) {
		 	String back=URLDemo.sendGet("http://api.revth.com/history/"+id,2);
			JSONArray pai=JSONArray.parseArray(new JSONObject().parseObject((new JSONObject().parseObject(back)).getString("data")).getString("detail").toString());
			back="";
			for(int i=0;i<pai.size();i++)
			{
				back=back+"玩家:"+pai.getJSONObject(i).getString("name")+"\n本局分数:"+pai.getJSONObject(i).getIntValue("score")+"\n出牌:"+pai.getJSONObject(i).getString("card")+"\n\n";
			}
			return back;
	 }
}