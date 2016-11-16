package fulton.util.android.register;

import java.util.HashMap;
/**
 * 
 * @author Douglas
 *
 */
public interface HttpRequest {
	
	public HttpResponse request(String url,String method,HashMap<String, String> headers,String data);
	
	public HttpResponse get(String url,HashMap<String, String> headers);
	
	public HttpResponse post(String url,HashMap<String, String> headers,String data);
	
	public void setHeaders(HashMap<String, String> headers);
	public void setHeader(String key,String value);
	public void setHost(String host);
	public void setCookie(String cookie);
	public void setConnectTimeout(int timeout);
	public void setReadTimeout(int timeout);
	public void setEncoding(String encoding);
	
	public void enableDefault();
	public void disableDefault();
	
	public void duplicateRequest();
}
