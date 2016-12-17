package fulton.util.net;

import java.util.HashMap;

import fulton.util.java.Util;
import fulton.util.net.register.BaiduLoggedIn;
import fulton.util.net.register.LoginRequester;

public class HowToUse {

	
	
	
	public static void main(String...args)
	{
		useHttpRequest();
		
	}
	
	public static void useLoginManager()
	{
		LoginRequester lrq=new BaiduLoggedIn();
		lrq.login();
		Util.print("Is logged in? ",lrq.isLogin());
		
		lrq.getUserName();
		lrq.getPassword();
		
		lrq.logout();
	}
	public static void useHttpRequest()
	{
		HttpHeader baseHeader=new HttpHeader();
		HttpRequest h=new NativeHttpRequest(baseHeader);
		
		baseHeader.setUserAgent(HttpHeader.VAL_UA_MOZZILA5_FULTON);
		h.enableDefault();
		
		HttpResponse response=h.get("http://www.baidu.com/s?q=we");
		//System.out.println(response.getBody());
		//System.out.println(response.getStatus());
		//System.out.println(response.getHeaders());
		//h.get("www.baidu.com", );
		
		Util.print(response.getStatus(),response.getResponse());
		Util.print(response.getHeader());
		Util.print(response.getBody());
	}
	
	public static void help()
	{
	/*
		Of course this is very useful.Once completed,can be a huge step
		of my university life.So seriously speaking,I'm very glad to wait
		its complishment
	*/
	}
}
