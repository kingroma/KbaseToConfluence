package confluence.retrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// example
//{
//	"type":"page",
//	"title":"API TEST POST CONTENT",
//	"space":{
//			"key":"PRDSDP10"
//	},
//	"ancestors":[{"id":47059552}],
//	"body":{
//		"view":{
//			"value":"<p>Hello World API TEST</p>",
//			"representation":"storage"
//		}
//	}
//}

public class RetrofitContentRequest {
	private String type = "page";
	
	private String title = null ; 
	
	private _space space = new _space("PRDSDP10") ; 
	
	private List<Map<String,Object>> ancestors = new ArrayList<Map<String,Object>>() ; 
	
	private _body body = new _body(); ; 
	
	public RetrofitContentRequest() {
		body.putStorage("representation", "storage");
	}
	
	public RetrofitContentRequest(
			String title , 
			Integer ancestorsId ,
			String value 
		) {
		body.putStorage("representation", "storage");
		this.title = title ; 
		this.body.putStorage("value", value);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", ancestorsId);
		this.addAncestors(map);
	}
	
	public boolean requestValid() {
		boolean result = true ;
		
		if ( type == null || type.isEmpty() ) {
			result = false ;
			System.out.println("type is null or empty");
		}
		
		else if ( title == null || title.isEmpty() ) {
			result = false ; 
			System.out.println("title is null or empty");
		}
		
		else if ( space == null || 
				space.getKey() == null || space.getKey().isEmpty() ) {
			result = false ; 
			System.out.println("space key is null or empty");
		}
		
		else if ( ancestors == null || ancestors.isEmpty() ) {
			result = false ; 
			System.out.println("ancestors is null or empty");
		} 
		
		else if ( ancestors.get(0) == null || ancestors.get(0).get("id") == null ){
			result = false ; 
			System.out.println("ancestors id is null");
		}
		
		else if ( body == null ) {
			result = false ;
			System.out.println("body is null");
		} 
		
		else if ( body.getStorage() == null ) {
			result = false ; 
			System.out.println("body view is null");
		}
		
		else if ( body.getStorage().get("value") == null || body.getStorage().get("value").isEmpty() || 
				body.getStorage().get("representation") == null || body.getStorage().get("representation").isEmpty()  
				) {
			result = false ; 
			System.out.println("body value or representation is null or empty");
		}
		
		return result ; 
	}
	
	public class _space {
		private String key = null ;

		public _space(){}
		
		public _space(String key) {
			this.key = key ;
		}
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		} 
	}
	
	public class _body {
		private Map<String,String> storage = null ;

		public Map<String, String> getStorage() {
			return storage;
		}

		public void setStorage(Map<String, String> storage) {
			this.storage = storage;
		}
		
		public void putStorage(String key, String value) {
			if ( storage == null ) {
				storage = new HashMap<String,String>();
			}
			storage.put(key, value);
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public _space getSpace() {
		return space;
	}

	public void setSpace(_space space) {
		this.space = space;
	}

	public List<Map<String, Object>> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<Map<String, Object>> ancestors) {
		this.ancestors = ancestors;
	}
	
	public void addAncestors(Map<String,Object> map) {
		if ( this.ancestors == null ) {
			this.ancestors = new ArrayList<Map<String,Object>>();
		}
		
		this.ancestors.add(map);
	}
	
	public _body getBody() {
		return body;
	}

	public void setBody(_body body) {
		this.body = body;
	}
	
	
	
}
