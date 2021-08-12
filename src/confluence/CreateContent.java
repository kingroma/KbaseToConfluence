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
		
//		String str = "<col width=\"24\">";
//		str = "<div> \r\n"
//				+ "   <col width=\"100%\">asd\r\n"
//				+ "  </colgroup>\r\n"
//				+ "  <tbody>\r\n"
//				+ "   <tr>\r\n"
//				+ "    <td valign=\"top\"><img src=\"/confluence/images/icons/emoticons/information.gif\" width=\"16\" height=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\"></td>\r\n"
//				+ "    <td>어플의 메모리 캐쉬방식에서 오버플로우가 발생하는 경우에 대한 처리를 감안한다. FIFO방식이나 우선순위에 따른 손실처리는 가정하며 이는 사용자의 수집 행위 정보가 100% 전달되지 않아도 된다. (확률적인 통계는 가능)</td>\r\n"
//				+ "</div>";
//		checkValue(str);
		// System.out.println(checkValue(str));
		
		String html = "";
		
		// html = "<td valign=\"top\"><img src=\"/confluence/images/icons/emoticons/information.gif\" width=\"16\" height=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\"/></td>\r\n";
		create("03. 서비스 API 명세서 - TBroad", 47059680, html);
		
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
					System.out.println("response success");
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
		
//		import org.apache.commons.lang3.StringEscapeUtils;
		// value = StringEscapeUtils.unescapeHtml3(value);
		
		// value = StringEscapeUtils.escapeHtml3(value);
		
		value = value.replaceAll("<sup><img class=\"rendericon\" src=\"/confluence/images/icons/linkext7.gif\" height=\"7\" width=\"7\" align=\"absmiddle\" alt=\"\" border=\"0\"></sup>", "");
		value = value.replaceAll("<br class=\"atl-forced-newline\">", "<br class=\"atl-forced-newline\" />");
		value = value.replaceAll("<img class=\"emoticon\" src=\"/confluence/images/icons/emoticons/star_red.gif\" height=\"16\" width=\"16\" align=\"absmiddle\" alt=\"\" border=\"0\">","★");
		
		value = value.replaceAll("<div class=\"code panel\" style=\"border-width: 1px;\">", "<div class=\"preformatted panel conf-macro output-block\" style=\"border-width: 1px;\" data-hasbody=\"true\" data-macro-name=\"noformat\">");
		value = value.replaceAll("<div class=\"preformatted panel\" style=\"border-width: 1px;\">", "<div class=\"preformatted panel conf-macro output-block\" style=\"border-width: 1px;\" data-hasbody=\"true\" data-macro-name=\"noformat\">");
		
		value = value.replaceAll("<script type=\"syntaxhighlighter\" class=\"toolbar: false; theme: Eclipse; brush: cpp; gutter: false\"><!\\[CDATA\\[", "<pre>");
		value = value.replaceAll("\\]\\]></script>", "</pre>");
		value = value.replaceAll("codeContent","preformattedContent");
		
		value = value.replaceAll(col_regex1,"<col />");
		value = value.replaceAll(col_regex2,"<col width=\"$1\"/>");
		
		value = value.replaceAll(img_regex1, "<img $1/>");
		
		System.out.println("===================");
		System.out.println(value);
		System.out.println("===================");
		return value ; 
	}
}
