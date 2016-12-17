package fulton.util.net;

import java.util.HashMap;
import java.util.List;
import java.awt.print.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;


public interface HttpResponse {
	public int getStatus();
	public void setStatus(int status);
	public String getResponse();
	public void setResponse(String resp);
	public HttpHeader getHeader();
	public void setHeader(HttpHeader header);
	public Object getBody();/*The body may be String or Array-of-char,
			based on content-type & charset*/
	public void setBody(Object body);
	
	public void fromRaw(HttpURLConnection conn) throws Exception;	
}
