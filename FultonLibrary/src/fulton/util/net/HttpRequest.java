package fulton.util.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Douglas
 *
 */
public interface HttpRequest {

	
	
	public HttpResponse request(String url,String method,Map<String, List<String>> headers,String data);
	public HttpResponse get(String url,Map<String, List<String>> headers);
	public HttpResponse get(String url);
	public HttpResponse post(String url,Map<String, List<String>> headers,String data);
	public HttpResponse post(String url,String data);
	
	public HttpHeader getBaseHeader();
	public void setBaseHeader(HttpHeader baseHeader);
	
	public void setResponseTemplate(Class clz);
	public Class getResponseTemplate();
	public void setConnectTimeout(int timeout);
	public int getConnectTimeout();
	public void setReadTimeout(int timeout);
	public int getReadTimeout();
	public void setHttpVersion(String version);
	public String getHttpVersion();
	
	public void enableDefault();
	public void disableDefault();
	public boolean defaultEnabled();
	
	
	public HttpRequest duplicateRequest();
}
