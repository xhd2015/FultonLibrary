package fulton.util.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fulton.util.java.Util;

public class NativeHttpResponse implements HttpResponse{

	protected int status=HttpHeader.STATUS_200;
	protected String response=HttpHeader.RESPONSE_OK;
	protected String version=HttpHeader.VERSION_HTTP_1_1;
	protected HttpHeader header;
	protected Object body;		/*String or array of chars*/
	
	public NativeHttpResponse()
	{
	}
	@Override
	public int getStatus() {
		return status;
	}
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public Object getBody() {
		return body;
	}
	@Override
	public void setBody(Object body) {
		this.body = body;
	}
	public void fromRaw(HttpURLConnection conn) throws IOException
	{
		NativeHttpResponse resp=this;
		HttpHeader header=new HttpHeader();
		resp.setStatus(conn.getResponseCode());
		resp.setResponse(conn.getResponseMessage());
		Map<String, List<String>> gHeaders=conn.getHeaderFields();	//non-standard,such as "COOKIE:x"
		for(Entry<String, List<String>> obj:gHeaders.entrySet())
		{
			header.setHeaders(obj.getKey(), obj.getValue());
		}
		resp.setHeader(header);
		String charset=header.getCharset();//If charset is null,it is not text
		
		int length=conn.getContentLength();
		BufferedInputStream in=new BufferedInputStream(conn.getInputStream());
		byte[] buf;
		if(charset==null)
		{
			buf=new byte[length];
			in.read(buf);
			resp.setBody(buf);
		}else{
			buf=new byte[1024];
			StringBuilder str=new StringBuilder();
			while((in.read(buf))!=-1)
			{
				str.append(new String(buf, charset));
			}
			resp.setBody(str.toString());
		}
		in.close();
		
		//finished
	}

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public void setResponse(String resp) {
		// TODO Auto-generated method stub
		response=resp;
	}

	@Override
	public HttpHeader getHeader() {
		// TODO Auto-generated method stub
		return header;
	}

	@Override
	public void setHeader(HttpHeader header) {
		// TODO Auto-generated method stub
		this.header=header;
	}
}
