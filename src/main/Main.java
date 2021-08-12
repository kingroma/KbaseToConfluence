package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import kbase.FindDoc;
import kbase.obj.DocObj;

public class Main {
	// https://docs.atlassian.com/atlassian-confluence/REST/6.6.0/
	// https://confluence.alticast.com/rest/api/content/5816986?expand=body
	public static void main(String[] args) {
		
		Integer kbasePageId = 44798882 ;
		
		Integer conflunenceParentId =  47059680 ; 
		
		DocObj docObj = FindDoc.find(kbasePageId);
		
		Document doc = Jsoup.parse(docObj.getBody());
		
		System.out.println(doc);
		// System.out.println(docObj);
		
		// Map<String,Object> map = CreateContent.create("" + docObj.getTitle() + "" ,conflunenceParentId,docObj.getBody());
		
		// System.out.println(map.get("id"));
	}
	
	
}
