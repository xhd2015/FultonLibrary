package fulton.util.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.kerberos.KerberosTicket;
import javax.xml.ws.http.HTTPBinding;
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

	protected boolean defaultEnabled=true;
	protected int timeoutConn=1000;
	protected int timeoutRead=1000;
	protected String charset="UTF-8";//with charset,should this be changed
	protected Class template=NativeHttpResponse.class;
	protected HttpHeader baseHeader;
	protected HttpURLConnection conn;
	protected String version;

	public NativeHttpRequest() {
		// TODO Auto-generated constructor stub
		baseHeader=new HttpHeader();
		initDefault();
	}
	public NativeHttpRequest(HttpHeader header)
	{
		setBaseHeader(header);
	}
	
	/**
	 * This is not very effective if every time an Http request happens
	 */
	@Override
	public HttpResponse request(String url, String method, Map<String, List<String>> headers, String data) {
		// TODO Auto-generated method stub
		
		try {
			URL url2=new URL(url);
			HttpURLConnection conn=(HttpURLConnection)url2.openConnection();
			boolean doout=method.equals(HttpHeader.METHOD_POST);
			conn.setDoOutput(doout);
			conn.setDoInput(true);
			if(defaultEnabled)
			{
				Map<String, List<String>> x = baseHeader.handupVirgin();
				for(Entry<String, List<String>> obj:x.entrySet())
				{
					String k=obj.getKey();
					for(String s:obj.getValue())
						conn.addRequestProperty(k,s);
				}
			}
			if(headers!=null)
			{
				for(Entry<String, List<String>> obj:headers.entrySet())
				{
					String k=obj.getKey();
					for(String s:obj.getValue())
						conn.setRequestProperty(k,s);
				}
			}
			conn.setRequestMethod(method);
			conn.connect();//send headers
			
			String xcharset=baseHeader.getCharset();
			if(xcharset!=null)
			{
				charset=xcharset;
			}
			if(doout)
			{
				DataOutputStream out=new DataOutputStream(conn.getOutputStream());
				out.write(data.getBytes(charset));
				out.flush();
				out.close();
			}
			
			
			HttpResponse response=null;
			try {
				response = (HttpResponse)getResponseTemplate().newInstance();
				response.fromRaw(conn);
				return response;
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) { /*fromRaw error,this should never happen*/
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//This should never happen,because the class should always be HttpResponse.
			return null;
	
			
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
	public HttpResponse get(String url, Map<String, List<String>> headers) {
		// TODO Auto-generated method stub
		return request(url,HttpHeader.METHOD_GET, headers,null);
	}

	@Override
	public HttpResponse post(String url,Map<String, List<String>> headers, String data) {
		// TODO Auto-generated method stub
		return request(url, HttpHeader.METHOD_POST, headers, data);
	}


	@Override
	public void enableDefault() {
		// TODO Auto-generated method stub
		defaultEnabled=true;
	}

	@Override
	public void disableDefault() {
		// TODO Auto-generated method stub
		defaultEnabled=false;
	}

	@Override
	public HttpRequest duplicateRequest() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public HttpResponse get(String url) {
		// TODO Auto-generated method stub
		return get(url,null);
	}
	@Override
	public HttpResponse post(String url, String data) {
		// TODO Auto-generated method stub
		return post(url, null,data);
	}

	@Override
	public boolean defaultEnabled() {
		// TODO Auto-generated method stub
		return defaultEnabled;
	}
	public void initDefault() {
		// TODO Auto-generated method stub
		disableDefault();
		baseHeader.setHeader(HttpHeader.KEY_CONTENTTYPE,HttpHeader.VAL_TYPE_TEXTHTML+"; "+
					HttpHeader.VAL_CHARSET_UTF8);
		baseHeader.setUserAgent(HttpHeader.VAL_UA_MOZZILA5_FULTON);
		enableDefault();
	}
	@Override
	public void setResponseTemplate(Class clz) {
		// TODO Auto-generated method stub
		this.template=clz;
	}
	@Override
	public Class getResponseTemplate() {
		// TODO Auto-generated method stub
		return this.template;
	}
	@Override
	public HttpHeader getBaseHeader() {
		// TODO Auto-generated method stub
		return baseHeader;
	}
	@Override
	public void setBaseHeader(HttpHeader baseHeader) {
		// TODO Auto-generated method stub
		this.baseHeader=baseHeader;
	}
	@Override
	public void setConnectTimeout(int timeout) {
		// TODO Auto-generated method stub
		this.timeoutConn=timeout;
	}
	@Override
	public int getConnectTimeout() {
		// TODO Auto-generated method stub
		return timeoutConn;
	}
	@Override
	public void setReadTimeout(int timeout) {
		// TODO Auto-generated method stub
		this.timeoutRead=timeout;
	}
	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return timeoutRead;
	}
	@Override
	public void setHttpVersion(String version) {
		// TODO Auto-generated method stub
		this.version=version;
	}
	@Override
	public String getHttpVersion() {
		// TODO Auto-generated method stub
		return version;
	}

}
