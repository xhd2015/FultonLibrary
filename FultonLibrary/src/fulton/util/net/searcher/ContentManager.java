package fulton.util.net.searcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import fulton.util.net.searcher.processors.AskubuntuProcessor;
import fulton.util.net.searcher.processors.BaiduZhidaoProcessor;
import fulton.util.net.searcher.processors.GuokrProcessor;
import fulton.util.net.searcher.processors.StackOverflowProcessor;
import fulton.util.net.searcher.processors.YahooProcessor;
import fulton.util.net.searcher.processors.ZhihuProcessor;

public class ContentManager {
	static String userAgent="Mozilla/5.0 (JSoup)";
	static String encoding="UTF8";
	public static HashMap<String, Class> mapper; 
	public static HashMap<String, ContentProcessor> pool;
	public static final String 
			YAHOO="yahoo",
			STACKOVERFLOW="stackoverflow",
			BAIDUZHIDAO="baiduzhidao",
			ASKUBUNTU="askubuntu",
			GUOKR="guokr",
			ZHIHU="zhihu";
	
	static{
		pool=new HashMap<>();
		mapper=new HashMap<>();
		mapper.put(YAHOO, YahooProcessor.class);
		mapper.put(STACKOVERFLOW, StackOverflowProcessor.class);
		mapper.put(BAIDUZHIDAO,BaiduZhidaoProcessor.class);
		mapper.put(ASKUBUNTU, AskubuntuProcessor.class);
		mapper.put(GUOKR, GuokrProcessor.class);
		mapper.put(ZHIHU, ZhihuProcessor.class);
	}
	public static ContentProcessor getProcessor(String type)
	{
		if(!pool.containsKey(type))
		{
			Class c=mapper.get(type);
			try {
				pool.put(type,(ContentProcessor) c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return pool.get(type);
	}
	public static Document getDocument(String url)
	{
		try {
			return Jsoup.connect(url).userAgent(userAgent).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static String getUrl(String word,String type)
	{
		ContentProcessor p=getProcessor(type);
		String nword;
		try {
			nword = URLEncoder.encode(word,encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			nword=word;
		}
		return p.getBaseUrl()+"?"+String.format(p.getParameterFormater(),nword);
	}
	public static void saveAsFile(String filename,String content)
	{
		try {
			BufferedWriter w=new BufferedWriter(new FileWriter(new File(filename)));
			w.write(content);
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//--------------- These ARE NOT original (means they're based on the functions previous defined------------
	public static ArrayList<HashMap<String,String>> searchFor(String word,String type)
	{
		String url=ContentManager.getUrl(word, type);
		Document doc=ContentManager.getDocument(url);
		ContentProcessor p=getProcessor(type);
		return p.process(doc);
	}
}
