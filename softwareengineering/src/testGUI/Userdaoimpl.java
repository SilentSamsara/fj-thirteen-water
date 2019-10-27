package testGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//import secondpairing.Main;
import testGUI.Userdao;
import testGUI.User;

public class Userdaoimpl implements Userdao{


	 
	@Override
	public void regist(User user) {
		System.out.println(user.get_rjson());
		try {			
			HttpURLConnection urlConnection=null;
		    URL url=new URL("http://api.revth.com/auth/register2" );
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(5000);
			urlConnection.setUseCaches(false);
			urlConnection.connect();
			
			/*PrintWriter pw=new PrintWriter(urlConnection.getOutputStream());
			pw.print(user.get_json());
			pw.flush();*/

			BufferedReader bf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String line=bf.readLine();
			while(line!=null) {
			System.out.println(line);
			line=bf.readLine();
			}
			
	}catch (Exception e) {
		System.out.println("sendPost error!");
		}
	}

	@Override
	public void login(User user) {
		System.out.println(user.get_json());
		try {			
			HttpURLConnection urlConnection=null;
		    URL url=new URL("http://api.revth.com/rank");
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(5000);
			urlConnection.setUseCaches(false);
			urlConnection.connect();
			
			/*PrintWriter pw=new PrintWriter(urlConnection.getOutputStream());
			pw.print(user.get_json());
			pw.flush();*/
//
//			BufferedReader bf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
//			String line=bf.readLine();
//			while(line!=null) {
//			System.out.println(line);
//			line=bf.readLine();
//			}
			
	}catch (Exception e) {
		System.out.println("sendPost error!");
		}

		
		
	}

}
