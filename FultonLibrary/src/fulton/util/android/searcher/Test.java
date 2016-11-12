package fulton.util.android.searcher;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word="kmp";
		String type="askubuntu";
		String url = ContentManager.getUrl(word, type);
		Document docu = ContentManager.getDocument(url);
		ContentManager.saveAsFile("C:\\Users\\sz\\Desktop\\askubuntu.html", docu.toString());
	}
}
