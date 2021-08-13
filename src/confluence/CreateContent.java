package confluence;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import confluence.retrofit.RetrofitConfluenceClient;
import confluence.retrofit.RetrofitConfluenceService;
import confluence.retrofit.RetrofitContentRequest;
import retrofit2.Call;
import retrofit2.Response;
import util.Static;

public class CreateContent {
	
	public static void main(String[] args) {
//		String title = "CREATE CONTENT API POST" ; 
//		Integer ancestorsId = 47059552 ;
//		String value = "<p>HELLO WORLD THIS IS REST API TEST</p>" ; 
//		
//		create(title, ancestorsId, value);
		
		String str = "<td><b>구매목록 숨김</b><br />"
				+ "시스템에서 구매 건당 각각의 hidden value ( T, M, F ) 3가지 값을 가진다. "
				+ "<p>&nbsp; &nbsp;-default = ' <b>F</b> '</p> <p>&nbsp; &nbsp;-admin 으로 "
				+ "<span class=\"nobr\"><a href=\"/confluence/pages/createpage.action?spaceKey=WINDMILL&amp;title=POST users.USER_ID.purchase.PURCHASE_ID.hidden - TBroad\" class=\"createlink\">hidden <sup><img class=\"rendericon\" "
				+ "src=\"/confluence/images/icons/plus.gif\" height=\"7\""
				+ " width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\" /></sup></a></span>을 하면"
				+ " ' <b>T</b> '&nbsp;</p> <p>&nbsp; &nbsp;-user, family 로 <span class=\"nobr\">"
				+ "<a href=\"/confluence/pages/createpage.action?spaceKey=WINDMILL&amp;title=POST "
				+ "users.USER_ID.purchase.PURCHASE_ID.hidden - TBroad\" class=\"createlink\">"
				+ "hidden <sup><img class=\"rendericon\" src=\"/confluence/images/icons/plus.gif\""
				+ " height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\" /></sup></a>"
				+ "</span>을 하면 ' <b>M</b> '</p> <p>위 hidden 값 들은 &nbsp;"
				+ "<span class=\"nobr\"><a href=\"/confluence/pages/"
				+ "createpage.action?spaceKey=WINDMILL&amp;title=GET"
				+ " users.USER_ID.purchase - TBroad\" class=\"createlink\">구매리스트<sup><img class=\"rendericon\" src=\"/confluence/images/icons/plus.gif\" height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\" /></sup></a></span>&nbsp;를&nbsp;호출하는 '<b>계정 타입'</b> 과 <b>&nbsp;'include_hidden</b>' &nbsp;parameter 에 따라 아래표와 같이 노출&nbsp;을 결정 하는 역할을 한다.</p></td>";
		checkValue(str);
		// System.out.println(checkValue(str));
		
//		String html = "";
		
		// html = "<td valign=\"top\"><img src=\"/confluence/images/icons/emoticons/information.gif\" width=\"16\" height=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\"/></td>\r\n";
//		create("03. 서비스 API 명세서 - TBroad", 47059680, html);
		
	}
	
	
	public static Map<String,Object> create(String title , Integer ancestorsId , String value) {
		Map<String,Object> result = new HashMap<String,Object>() ; 
		
		value = checkValue(value);
		
//		System.exit(1);
		
		RetrofitContentRequest request = new RetrofitContentRequest(title, ancestorsId, value) ; 
				
		if ( request.requestValid() ) {
			RetrofitConfluenceService service = RetrofitConfluenceClient.getService();
			Call<Map<String,Object>> call = service.createPage(Static.getConfluenceAuth(), request);
			try {
				Response<Map<String,Object>> response = call.execute() ; 
				
				if ( response.isSuccessful() ) {
					// System.out.println("response success");
					// System.out.println(response.body());
					result = response.body();
				} else {
					System.out.println("response fail");
					System.out.println(response.message());
					System.out.println(response.errorBody().string());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result ; 
	}
	
	private static final String SPACE = "[ \t]*";
	// <col width="24">
	private static final String col_regex1 = "<col[ \t]*>";
	private static final String col_regex2 = "<col[ \t]*width[ \t]*=['\"]([0-9%]*)['\"][ \t]*>";
	
	// <img src="/confluence/images/icons/emoticons/information.gif" width="16" height="16" align="absmiddle" alt="" border="0">
	private static final String img_regex1 = "<img" + SPACE + "([^>]*" + "[\"' \t])" + ">"; 
	
	private static final String per_regex1 = "[^'\"][0-9]*%[^'\"]";;
	
	public static String checkValue(String value) {
		value = value.replaceAll("<br>", "<br />");
		value = value.replaceAll("100%","100퍼");
		try {
			value = URLDecoder.decode(value,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(value);
//		import org.apache.commons.lang3.StringEscapeUtils;
		// value = StringEscapeUtils.unescapeHtml3(value);
		
		// value = StringEscapeUtils.escapeHtml3(value);
		
//		value = value.replaceAll("<sup><img class=\"rendericon\" src=\"/confluence/images/icons/linkext7.gif\" height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\"></sup>", "");
		value = value.replaceAll("<br class=\"atl-forced-newline\">", "<br class=\"atl-forced-newline\" />");
//		value = value.replaceAll("<img class=\"emoticon\" src=\"/confluence/images/icons/emoticons/star_red.gif\" height=\"16\" width=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\">","★");
		
		value = value.replaceAll("<div class=\"code panel\" style=\"border-width: 1px;\">", "<div class=\"preformatted panel conf-macro output-block\" style=\"border-width: 1px;\" data-hasbody=\"true\" data-macro-name=\"noformat\">");
		value = value.replaceAll("<div class=\"preformatted panel\" style=\"border-width: 1px;\">", "<div class=\"preformatted panel conf-macro output-block\" style=\"border-width: 1px;\" data-hasbody=\"true\" data-macro-name=\"noformat\">");
		
		value = value.replaceAll("<script type=\"syntaxhighlighter\" class=\"toolbar: false; theme: Eclipse; brush: cpp; gutter: false\"><!\\[CDATA\\[", "<pre>");
		value = value.replaceAll("\\]\\]></script>", "</pre>");
		value = value.replaceAll("codeContent","preformattedContent");
		
		value = value.replaceAll("<img class=\"emoticon\" src=\"/confluence/images/icons/emoticons/star_red.gif\" height=\"16\" width=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\" />", "<span style=\"color:red\">★</span>");
		value = value.replaceAll("<img class=\"emoticon\" src=\"/confluence/images/icons/emoticons/star_yellow.gif\" height=\"16\" width=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\" />", "<span style=\"color:yellow\">★</span>");
		value = value.replaceAll("<sup><img class=\"rendericon\" src=\"/confluence/images/icons/plus.gif\" height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\" /></sup>", "");
		value = value.replaceAll("<img class=\"rendericon\" src=\"/confluence/images/icons/plus.gif\" height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\" />", "");
		
		// value = value.replaceAll("<span class=\"nobr\">(.*)class=\"createlink\"(.*)</span>","$1$2");
		value = value.replaceAll("createlink", "");
		
		value = value.replaceAll("<img src=\"/confluence/images/icons/emoticons/information.gif\" width=\"16\" height=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\" />",
				"<span style=\"color:blue\">★</span>");
				
		value = value.replaceAll("<categorylist>", "");
//		value = value.replaceAll(col_regex1,"<col />");
//		value = value.replaceAll(col_regex2,"<col width=\"$1\"/>");
//		
//		value = value.replaceAll(img_regex1, "<img $1/>");
		
//		System.out.println("===================");
//		System.out.println(value);
//		System.out.println("===================");
		return value ; 
	}
}
