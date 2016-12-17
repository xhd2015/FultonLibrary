package fulton.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil {
	
	public static void put(Map<String, List<String>> m,String key,String value)
	{
		getOrNew(m, key).add(value);
	}
	
	public static List<String> getOrNew(Map<String, List<String>> m,String key)
	{
		List<String> list=m.getOrDefault(key, null);
		if(list==null)
		{
			list=new ArrayList<>();
			m.put(key, list);
		}
		return list;
	}
}
