package fulton.util.net.register;

import java.util.List;
import java.util.Map;

import fulton.util.net.HttpHeader;
import fulton.util.net.HttpRequest;
import fulton.util.net.HttpResponse;

public class BaiduLoggedIn implements LoginRequester {

	@Override
	public HttpResponse request(String url, String method, Map<String, List<String>> headers, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse get(String url, Map<String, List<String>> headers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse get(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse post(String url, Map<String, List<String>> headers, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse post(String url, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpHeader getBaseHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBaseHeader(HttpHeader baseHeader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResponseTemplate(Class clz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class getResponseTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnectTimeout(int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getConnectTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setReadTimeout(int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean defaultEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HttpRequest duplicateRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHttpVersion(String version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHttpVersion() {
		// TODO Auto-generated method stub
		return null;
	}


}
