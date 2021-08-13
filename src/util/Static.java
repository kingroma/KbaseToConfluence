package util;

import org.apache.commons.codec.binary.Base64;

public class Static {
	public static final String CONFLUENCE_USERNAME = "jj.kim" ; 
	public static final String CONFLUENCE_PASSWORD = "Abtbg31#" ;
	
	public static final String KBASE_JSESSIONID = "01D57AE0B28B44CE56B0E84B9A7A0A14";
	
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
