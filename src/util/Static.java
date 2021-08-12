package util;

import org.apache.commons.codec.binary.Base64;

public class Static {
	public static final String CONFLUENCE_USERNAME = "jj.kim" ; 
	public static final String CONFLUENCE_PASSWORD = "Abtbg31#" ;
	
	public static final String KBASE_JSESSIONID = "91696FA9C0B8FF4AA94DD9A5FF4D2C7D";
	
	public static String getConfluenceAuth() {
		return "Basic " + Base64.encodeBase64String(
					(
							CONFLUENCE_USERNAME + 
							":" + 
							CONFLUENCE_PASSWORD
					).getBytes()
				);
	}
}
