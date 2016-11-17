package fulton.util.android.register;

import java.util.HashMap;
import java.util.List;
import java.awt.print.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;


public interface HttpResponse {
	public static final String KEY_COOKIE="Set-Cookie";
	public int getStatus();
	
	public Map<String, List<String>> getHeaders();
	public List<String> getFullHeader(String key);
	public String getHeader(String key);
	
	public void fromRaw(HttpURLConnection conn) throws Exception;	
}
