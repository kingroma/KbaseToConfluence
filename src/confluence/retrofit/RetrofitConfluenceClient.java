package confluence.retrofit;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://confluence.alticast.com/rest/api/content/5816963?expand=body.view
public class RetrofitConfluenceClient {
	private static final String CONFLUENCE_URL = "https://confluence.alticast.com/rest/api/";
	
	private static Retrofit RETROFIT = null ; 
	
	private static RetrofitConfluenceService SERVICE ; 
	
	public static void main(String[] args) {
		try {
			Call<Map<String,Object>> call = RetrofitConfluenceClient.getService().getPage("Basic " + btoa("jj.kim","Abtbg31#"),"5816963", "body.view");
			
			retrofit2.Response<Map<String,Object>> res = call.execute() ; 
			
			if ( res.isSuccessful() ) {
				System.out.println(res.body());
			} else {
				System.out.println("response fail");
				System.out.println(res.code());
				System.out.println(res.errorBody().string());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static String btoa(String id,String pw) {
		String result = null ; 
		
		// jj.kim:Abtbg31#
		return Base64.encodeBase64String((id+":"+pw).getBytes());  
	}
	
	public static RetrofitConfluenceService getService() {
		if ( SERVICE == null ) {
			SERVICE = getRetrofit().create(RetrofitConfluenceService.class);
		}
		
		return SERVICE ; 
	}
	
	private static Retrofit getRetrofit() {
		if ( RETROFIT == null ) {
			OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			      @Override
			      public Response intercept(Chain chain) throws IOException {
			        Request newRequest  = chain.request().newBuilder()
			            // .addHeader("Authorization", "userToken " + Static.USER_TOKEN)
			            .build();
			        return chain.proceed(newRequest);
			      }
			    }).build();
			
			RETROFIT = new Retrofit.Builder()
					.client(client)
			        .baseUrl(CONFLUENCE_URL)
			        .addConverterFactory(GsonConverterFactory.create())
			        .build();
		} 
		
		return RETROFIT ; 
	}
}
