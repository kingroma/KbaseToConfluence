package confluence.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitConfluenceService {
	
	// https://confluence.alticast.com/rest/api/content/5816963?expand=body.view
	@GET("content/{pageId}")
	public Call<Map<String,Object>> getPage(
			@Header("Authorization") String authorization , 
			@Path("pageId") String pageId , 
			@Query("expand") String expand 
			);
	
	// https://docs.atlassian.com/atlassian-confluence/REST/6.6.0/
	
//	curl -u admin:admin -X POST -H 'Content-Type: application/json' -d 
//	'{"type":"page","title":"new page",
//	"ancestors":[{"id":456}], "space":{"key":"TST"},"body":{"storage":{"value":
//	"<p>This is a new page</p>","representation":"storage"}}}'
//	http://localhost:8080/confluence/rest/api/content/ | python -mjson.tool
	
//	{
//		"type":"page",
//		"title":"API TEST POST CONTENT",
//		"space":{
//				"key":"PRDSDP10"
//		},
//		"ancestors":[{"id":47059552}],
//		"body":{
//			"view":{
//				"value":"<p>Hello World API TEST</p>",
//				"representation":"storage"
//			}
//		}
//	}
	@POST("content")
	public Call<Map<String,Object>> createPage(
			@Header("Authorization") String authorization ,
			@Body RetrofitContentRequest request 
			) ; 
}
