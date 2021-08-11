package confluence.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
	
	
}
