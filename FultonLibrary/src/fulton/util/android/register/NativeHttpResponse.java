package fulton.util.android.register;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NativeHttpResponse implements HttpResponse{

	private int status;
	private Map<String, List<String>> headers;
	private String body;
	
	public NativeHttpResponse()
	{
		headers=null;
	}
	
	public List<String> getFullHeader(String name)
	{
		return headers.get(name.toLowerCase());
	}
	public String getHeader(String name)
	{
		return getFullHeader(name).get(0);
	}
	

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void fromRaw(HttpURLConnection conn) throws IOException
	{
		NativeHttpResponse resp=this;
		resp.setStatus(conn.getResponseCode());
		Map<String, List<String>> gHeaders=conn.getHeaderFields();
		Map<String, List<String>> tHeaders=new HashMap<>();
		for(Entry<String, List<String>> obj:gHeaders.entrySet())
		{
			String key=obj.getKey();
			if(key!=null)
			{
				key=key.toLowerCase();
			}
			tHeaders.put(key, obj.getValue());
			
		}
		resp.setHeaders(tHeaders);
		
		String encoding=conn.getContentEncoding();
		if(encoding==null)
		{
			encoding="UTF-8";
		}
		BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
		byte[] buf=new byte[1024];
		StringBuilder str=new StringBuilder();
		while((in.read(buf))!=-1)
		{
			str.append(new String(buf, encoding));
		}
		in.close();
		resp.setBody(str.toString());
		
	}
	
	//==========Add ons
	public String getCookie()
	{
		return getHeader(KEY_COOKIE);
	}
}
