package fulton.util.android.register;

import java.util.HashMap;

public class HowToUse {

	
	
	
	public static void main(String...args)
	{
		useHttpRequest();
		
	}
	
	public static void useLoginManager()
	{
		
	}
	public static void useHttpRequest()
	{
		HttpRequest h=new NativeHttpRequest();
		HashMap<String, String> headers;
		h.setUserAgent(null);
		HttpResponse response=h.get("http://www.baidu.com/s?q=we");
		//System.out.println(response.getBody());
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders());
		//h.get("www.baidu.com", );
		System.out.println(response.getHeader(HttpResponse.KEY_COOKIE));
		
	}
}
