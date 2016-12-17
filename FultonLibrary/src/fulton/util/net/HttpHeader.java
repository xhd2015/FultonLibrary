package fulton.util.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fulton.util.MapUtil;
import fulton.util.java.Util;
/**
 * standard key : Cookie-->cookie
 * 
 * Very simple, you cannot specify a sole option,such as charset=UTF-8 without content=Text/html
 * And	such as
 * 		Accept-Encoding="gzip" is not valid.You should always say so:Accept-Encoding="gzip, deflate"
 * @author Douglas
 *
 */
public class HttpHeader {
	public static final String 
			METHOD_POST="POST",
			METHOD_GET="GET";
	public static final String 
			KEY_UA="User-Agent",
			KEY_CONTENTTYPE="Content-Type",
			KEY_HOST="Host",
			KEY_ACCEPTENCODING="Accept-Encoding",
			KEY_CONTENTLENGTH="Content-Length",
			KEY_UACPU="UA-CPU",
			KEY_CONNECTION="Connection",
			KEY_REFERER="Referer",
			KEY_ACCEPTLANGUANGE="Accept-Language",
			KEY_DATE="Date",
			KEY_PROGMA="Progma",
			KEY_XPOWEREDBY="X-Powered-by",
			KEY_EXPIRES="Expires",
			KEY_SERVER="Server",
			KEY_CACHECONTROL="Cache-Control",
			KEY_LASTMODIFIED="Last-Modified",
			KEY_LOCATION="Location",
			KEY_ETAG="ETag",
			KEY_TRANSFERENCODING="Transfer-Encoding",	/*chunked*/
			KEY_CONTENTENCODING="Content-Encoding",
			KEY_VARY="Vary",							/*Accept-Encoding*/
			KEY_COOKIE="Cookie",
			KEY_SETCOOKIE="Set-Cookie";
	
	/*KEY__XX is for combination*/
	public static final String
			KEY__CHARSET="_Charset",
			KEY__CONTENTTYPE="_Content-Type";
	public static final String
			VAL_CHARSET_UTF8="UTF-8",
			VAL_TYPE_TEXTHTML="text/html",
			VAL_UA_MOZZILA5_FULTON="Mozilla/5.0 (FultonRequester)";
	public static final String
			RESPONSE_OK="OK",
			RESPONSE_NOT_FOUND="Not Found";
	public static final String
			VERSION_HTTP_1_1="HTTP/1.1",
			VERSION_HTTP_1_0="HTTP/1.0";
	public static final int
			STATUS_200=200;
	
	protected Map<String, List<String>> defaultHeaders;
	protected String cachedStr;
	protected boolean modofied=true;
	
	public HttpHeader()
	{
		defaultHeaders=new HashMap<>();
	}
	
	public Map<String,List<String>> handupVirgin()
	{
		return defaultHeaders;
	}
	public void addHeader(String key,String value) {
		modofied=true;
		String skey=key.toLowerCase();
		MapUtil.getOrNew(defaultHeaders, skey).add(value);
	}
	public void addHeaders(String key,List<String> values) {
		modofied=true;
		String skey=key.toLowerCase();
		MapUtil.getOrNew(defaultHeaders, skey).addAll(values);
	}
	public void setHeader(String key,String value) {
		modofied=true;
		if(value==null)
		{
			setHeaders(key, null);
		}else{
			List<String> list=new ArrayList<>();
			list.add(value);
			setHeaders(key, list);
		}
	}
	public void setHeaders(String key,List<String> vals) {
		modofied=true;
		if(key==null)return;
		String skey=key.toLowerCase();
		if(vals==null )
		{
			if(defaultHeaders.containsKey(skey))
				defaultHeaders.remove(skey);
		}else{
			defaultHeaders.put(skey, vals);
		}
	}
	public void removeHeader(String key) {
		modofied=true;
		String skey=key.toLowerCase();
		defaultHeaders.remove(skey);
	}
	public List<String> getHeaders(String key) {
		String skey=key.toLowerCase();
		return defaultHeaders.getOrDefault(skey, null);
	}
	public String getHeader(String key) {
		List<String> list=getHeaders(key);
		if(list==null || list.isEmpty())
			return null;
		return list.get(0);
	}
	
	
	
	public void setHost(String host) {
		// TODO Auto-generated method stub
		setHeader(KEY_HOST, host);
	}
	public void setCookie(String cookie) {
		// TODO Auto-generated method stub
		setHeader(KEY_COOKIE, cookie);
	}
	public void setContentEncoding(String encoding) {
		// TODO Auto-generated method stub
		setHeader(KEY_CONTENTENCODING,encoding);
	}
	public String getUserAgent() {
		// TODO Auto-generated method stub
		return getHeader(KEY_UA);
	}
	public String getCookie()
	{
		return getHeader(KEY_COOKIE);
	}
	
	//@Special
	public void setContentType(String type) {
		modofied=true;
		String s=getHeader(KEY_CONTENTTYPE);
		StringBuilder xsBuilder=new StringBuilder();
		if(s!=null)
		{
			int end;
			end=s.indexOf(";");
			xsBuilder.append(type);
			if(end!=-1)
			{
				xsBuilder.append(s.substring(end));
			}
		}else{
			xsBuilder.append(type);
		}
		setHeader(KEY_CONTENTTYPE, xsBuilder.toString());
	}
	//@Special
	public String getContentType()
	{
		String s=getHeader(KEY_CONTENTTYPE);
		if(s!=null)
		{
			int end;
			end=s.indexOf(";");
			return s.substring(0, end);
		}else{
			return null;
		}
	}
	//@single
	public void setCharset(String charset) {
		modofied=true;
		String s=getHeader(KEY_CONTENTTYPE);
		StringBuilder xsBuilder=new StringBuilder();
		if(s!=null)
		{
			int start,end;
			start=s.indexOf(";");
			if(start==-1)
			{
				xsBuilder.append(s);
				xsBuilder.append("; ");
				xsBuilder.append(charset);
			}else{
				end = s.indexOf(";",start+1);
				xsBuilder.append(s.substring(0, start));
				xsBuilder.append("; ");
				xsBuilder.append(charset);
				if(end!=-1)
				{
					xsBuilder.append(s.substring(end));
				}
			}
		}else{
			xsBuilder.append(VAL_TYPE_TEXTHTML);
			xsBuilder.append("; ");
			xsBuilder.append(charset);
		}
		setHeader(KEY_CONTENTTYPE, xsBuilder.toString());
	}
	public String getCharset()
	{
		String s=getHeader(KEY_CONTENTTYPE);
		if(s!=null)
		{
			int start,end;
			start=s.indexOf(";");
			if(start==-1)
			{
				return null;
			}else{
				end = s.indexOf(";",start+1);
				if(end == -1)
				{
					end=s.length();
				}
				String region=s.substring(start+1, end);/*text/html; charset=utf-8 */
				String regex="\\s*(?:charset=)?([a-zA-Z0-9_\\-]+)";
				Matcher matcher=Pattern.compile(regex).matcher(region);
				matcher.find();
				return matcher.group(1);
			}
		}else{
			return null;
		}
	}
	
	public void setUserAgent(String ua) {
		// TODO Auto-generated method stub
		setHeader(KEY_UA, ua);
	}
	
	//@single
	public void addAcceptEncoding(String encoding)
	{
		modofied=true;
		String s=getHeader(KEY_ACCEPTENCODING);
		if(s==null)
		{
			setHeader(KEY_ACCEPTENCODING, encoding);
		}else if( !s.contains(encoding) ){
			setHeader(KEY_ACCEPTENCODING, s+", "+encoding);
		}
	}
	
	public String[] getAcceptEncoding()
	{
		String s=getHeader(KEY_ACCEPTENCODING);
		String regex=",\\s+";
		if(s!=null)
		{
			return Pattern.compile(regex).split(regex);
		}
		return null;
	}
	
	public String toString()
	{
		if(modofied)
		{
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder=new StringBuilder();
			for(Entry<String,List<String>> obj:defaultHeaders.entrySet())
			{
				String keyString=obj.getKey();
				for(String s:obj.getValue())
				{
					stringBuilder.append(String.format("%s: %s\r\n", keyString,s));
				}
			}
			cachedStr=stringBuilder.toString();
			modofied=false;
		}
		return cachedStr;
		
	}
	
}
