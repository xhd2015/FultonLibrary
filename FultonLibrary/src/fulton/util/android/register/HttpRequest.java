package fulton.util.android.register;

import java.util.HashMap;
/**
 * 
 * @author Douglas
 *
 */
public interface HttpRequest {
	public static final String POST="POST";
	public static final String GET="GET";
	public static final String KEY_UA="User-Agent";
	
	
	public HttpResponse request(String url,String method,HashMap<String, String> headers,String data);
	
	public HttpResponse get(String url,HashMap<String, String> headers);
	public HttpResponse get(String url);
	
	public HttpResponse post(String url,HashMap<String, String> headers,String data);
	public HttpResponse post(String url,String data);
	
	public void setHeaders(HashMap<String, String> headers);
	public void setHeader(String key,String value);
	public void setHost(String host);
	public void setCookie(String cookie);
	public void setConnectTimeout(int timeout);
	public void setReadTimeout(int timeout);
	public void setEncoding(String encoding);
	/**
	 * null to remove it
	 * @param ua
	 */
	public void setUserAgent(String ua);
	
	public void enableDefault();
	public void disableDefault();
	
	public void duplicateRequest();
}
