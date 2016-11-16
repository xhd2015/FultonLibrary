package fulton.util.android.register;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpResponse {
	private int status;
	private String cookie;
	private Map<String, List<String>> headers;
	private String body;
	
	public HttpResponse()
	{
		
	}
	public HttpResponse(Map<String, List<String>> headers)
	{
		this.headers=headers;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public static HttpResponse fromRaw(HttpsURLConnection conn)
	{
		HttpResponse resp=new HttpResponse();
		
		return resp;
	}
}
