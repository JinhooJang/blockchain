package com.tistory.needjarvis.module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 홀더 정보 모듈
 * 
 * @author Jinhoo Jang
 * @since 2018.12.07
 */
@Component
public class HolderModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HolderModule.class);

	
	/**
     * 홀더들의 리스트를 화면에 보여준다
     * 
     * @param  
     */
	public List<HashMap<String, String>> getHolderList() {
		List<HashMap<String, String>> result 
							= new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> blockItem;
		BufferedReader inFiles = null;
		StringBuffer sb = new StringBuffer();
				
		File[] blocks = new File("c:/steelj/chain").listFiles();
		Arrays.sort(blocks, Collections.reverseOrder());
		
		for(File block : blocks) {
			if(block.getName().equals("sequence"))
				continue;
						
			sb = new StringBuffer();
			
			try {
				inFiles = new BufferedReader(
						new InputStreamReader(
						new FileInputStream(
								block.getAbsolutePath()), "UTF8"));
				
				String line = "";
				while((line = inFiles.readLine()) != null) {
					if(line.trim().length() > 0) {
						sb.append(line);
					}
				}
				
				inFiles.close();
				
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(sb.toString());
				JSONArray ledgers = (JSONArray) obj.get("ledger");
				
				for(int i = 0; i < ledgers.size(); i++) {
					blockItem = new HashMap<String, String> ();
					JSONObject jsonObj = (JSONObject) ledgers.get(i);
					
					blockItem.put("address", (String)jsonObj.get("address"));
					blockItem.put("balance", (String)jsonObj.get("balance"));
					result.add(blockItem);
					
					blockItem = null;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("getBlockInfo : " + e.getMessage());			
			}
			
			break;
		}		
		
		return result;
	}
	
	
	/**
	 * 날짜 포맷 변환
	 * 
	 * @param date
	 * @return
	 */
	public String formatDate(String date) {
		return date.substring(0, 4) + "." + date.substring(4, 6) + "." + date.substring(6, 8)
				+ " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
	}
}
