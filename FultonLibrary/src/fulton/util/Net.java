package fulton.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import fulton.util.java.Util;

public class Net {
	
	public static void main(String[] args) throws IOException
	{
		String url="http://www.baidu.com/s?wd=lll";
		Util.print(getURL(url));
		
	}
	
	
	public static String getURL(String url)
	{
		try{
			URL u=new URL(url);
			URLConnection conn=u.openConnection();
			conn.connect();
			BufferedReader reader=new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			StringBuilder s=new StringBuilder();
			String temps=null;
			while((temps=reader.readLine())!=null)
			{
				s.append(temps);
				s.append("\n");
			}
			
			reader.close();
			
			return s.toString();
		}catch(Exception e){
			return "<Exception>"+e.toString();
		}
		

	}
}
