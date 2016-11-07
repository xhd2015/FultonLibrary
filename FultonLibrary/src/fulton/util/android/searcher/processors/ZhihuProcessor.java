package fulton.util.android.searcher.processors;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;

import fulton.util.android.searcher.ContentProcessor;

public class ZhihuProcessor implements ContentProcessor {

	@Override
	public ArrayList<HashMap<String, String>> process(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "zhihu";
	}

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return "http://www.zhihu.com/search";
	}

	@Override
	public String getParameterFormater() {
		// TODO Auto-generated method stub
		return "type=content&q=%s";
	}

}
