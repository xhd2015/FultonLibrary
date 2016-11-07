package fulton.util.android.searcher.processors;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;

import fulton.util.android.searcher.ContentProcessor;

public class StackOverflowProcessor implements ContentProcessor {

	@Override
	public ArrayList<HashMap<String, String>> process(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return "http://stackoverflow.com/search";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "stackoverflow";
	}


	@Override
	public String getParameterFormater() {
		// TODO Auto-generated method stub
		return "q=%s";
	}
}
