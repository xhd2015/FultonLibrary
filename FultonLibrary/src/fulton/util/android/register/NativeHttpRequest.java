package fulton.util.android.register;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * This native request just handles those streams that
 *  with text data,not binary data
 * And always return text data.
 * 
 * If content is compressed,then decompress it.As if it is never 
 * compressed.
 * @author Douglas
 *
 */
public class NativeHttpRequest implements HttpRequest {
	public static final String POST="post";
	private HashMap<String, String> defaultHeaders;
	private boolean defaultEnabled=true;
	private int timeoutConn=1000;
	private int timeoutRead=1000;
	private String encoding="UTF-8";
	
	public NativeHttpRequest() {
		// TODO Auto-generated constructor stub
		defaultHeaders=new HashMap<>();
		defaultHeaders.put("charset", "text/html; UTF-8");
	}
	@Override
	public HttpResponse request(String url, String method, HashMap<String, String> headers, String data) {
		// TODO Auto-generated method stub
		
		try {
			URL url2=new URL(url);
			HttpURLConnection conn=(HttpURLConnection)url2.openConnection();
			boolean doout=method.equals(POST);
			conn.setDoOutput(doout);
			conn.setDoInput(true);
			if(defaultEnabled)
			{
				for(Entry<String, String> obj:defaultHeaders.entrySet())
				{
					conn.setRequestProperty(obj.getKey(),obj.getValue());
				}
			}
			
			for(Entry<String, String> obj:headers.entrySet())
			{
				conn.setRequestProperty(obj.getKey(),obj.getValue());
			}
			conn.setConnectTimeout(timeoutConn);
			conn.setReadTimeout(timeoutRead);
			conn.setRequestMethod(method);
			conn.connect();//send headers
			
			if(doout)
			{
				DataOutputStream out=new DataOutputStream(conn.getOutputStream());
				out.write(data.getBytes(encoding));
				out.flush();
				out.close();
			}
			
			
			HttpResponse response=new HttpResponse();
			response.setStatus(conn.getResponseCode());
			
			
			
			return response;
	
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public HttpResponse get(String url, HashMap<String, String> headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse post(String url, HashMap<String, String> headers, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHeaders(HashMap<String, String> headers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeader(String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHost(String host) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCookie(String cookie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableDefault() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableDefault() {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicateRequest() {
		// TODO Auto-generated method stub

	}
	@Override
	public void setConnectTimeout(int timeout) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setReadTimeout(int timeout) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHeaders(HashMap<String, String> headers) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHeader(String key, String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setEncoding(String encoding) {
		// TODO Auto-generated method stub
		
	}

}