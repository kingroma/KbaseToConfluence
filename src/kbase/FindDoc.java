package kbase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kbase.obj.DocObj;
import util.Static;

public class FindDoc {
	private static String PAGE_ID = "50958401";
	
	public static void main(String[] args) {
		find("44799907");
	}
	
	public static DocObj find(Integer pageId) {
		return find(""+pageId);
	}
	
	public static DocObj find(String pageId) {
		DocObj obj = null ;  
		try {
			Document doc = 
					Jsoup
						.connect(getUrl(pageId))
						.cookie("JSESSIONID", Static.KBASE_JSESSIONID)
						.get();
			doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
			
//			String html = doc.html();
//			doc = Jsoup.parse(html);
			
			Element title_text = doc.getElementById("title-text");
			Element title_a = title_text.child(0);
			String title = title_a.html() ; 
			
			String body = doc.getElementsByClass("wiki-content").get(0).html();
//			System.out.println(body);
			
			obj = new DocObj() ;
			obj.setTitle(title);
			obj.setBody(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj ; 
	}
	
	private static String getUrl(String pageId) {
		return "http://kbase.alticast.com/confluence/pages/viewpage.action?pageId=" + pageId ;
	}
}


