package fulton.util.android.searcher.processors;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;

import fulton.util.android.searcher.ContentProcessor;

public class BaiduZhidaoProcessor implements ContentProcessor {

	@Override
	public ArrayList<HashMap<String, String>> process(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "baiduzhidao";
	}

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return "http://zhidao.baidu.com/search";
	}

	@Override
	public String getParameterFormater() {
		// TODO Auto-generated method stub
		return "lm=0&rn=10&pn=0&fr=search&ie=gbk&word=%s";
	}

}
