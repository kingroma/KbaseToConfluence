package kbase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kbase.obj.DocObj;

public class FindDoc {
	private static String JSESSIONID = "32066BF60789C7064B73B4C5EFFB975C" ;
	
	private static String PAGE_ID = "50958401";
	
	public static void main(String[] args) {
		find("44799907");
	}
	
	private static DocObj find(String pageId) {
		DocObj obj = null ;  
		try {
			Document doc = 
					Jsoup
						.connect(getUrl(pageId))
						.cookie("JSESSIONID", JSESSIONID)
						.get();
			
			Element title_text = doc.getElementById("title-text");
			Element title_a = title_text.child(0);
			String title = title_a.html() ; 
			
			String body = doc.getElementsByClass("wiki-content").get(0).html();
			System.out.println(body);
			
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


