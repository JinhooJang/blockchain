package com.tistory.needjarvis.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 피어를 관리하는 모듈
 * 
 * @author jinhoo.jang
 * @since 2018.08.13
 */
@Component
public class PeerModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerModule.class);
	
	/**
	 * 노드가 라이브된 상태인지 체크한다
	 * 
	 * @param addr
	 * @return
	 */
	public boolean callLive(String addr) {
		URL url = null;
	    BufferedReader reader = null;

	    try {
			url = new URL("http://" + addr + "/steelj/peer/check");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
		    con.setRequestMethod("GET");
		    con.setReadTimeout(1000);
		    con.connect();
		      	
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF8"));
		    
		    String inputLine = null;
		    while ((inputLine = reader.readLine()) != null) {
		    	System.out.println(inputLine);
		    }
		} catch (Exception e) {
			LOGGER.error("callLive error : " + e.getMessage());
			return false;
		} finally {
			if(reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
		}
	    
	    return true;
	}
}
