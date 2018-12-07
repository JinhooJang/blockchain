package com.tistory.needjarvis.module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 블록체인 모듈
 * 
 * @author Jinhoo Jang
 * @since 2018.12.07
 */
@Component
public class BlockModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockModule.class);

	
	/**
     * 최근의 블록 정보를 리스트 형태로 가져온다
     * 
     * @param string[] hashed,chainNo
     */
	public List<HashMap<String, String>> getBlockList(boolean isMain) {
		List<HashMap<String, String>> result 
							= new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> blockItem;
		BufferedReader inFiles = null;
		StringBuffer sb = new StringBuffer();
				
		File[] blocks = new File("c:/steelj/chain").listFiles();
		int cnt = 0;
		//Arrays.sort(blocks, Collections.reverseOrder());
		
		for(File block : blocks) {
			if(isMain && cnt >= 10)
				break;
			
			if(block.getName().equals("sequence"))
				continue;
			
			LOGGER.info("read block=>" + block.getName());
			blockItem = new HashMap<String, String> ();
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
				JSONObject header = (JSONObject) obj.get("header");
				
				blockItem.put("no", block.getName());
				blockItem.put("mined", (String)header.get("miner"));
				blockItem.put("reward", (String)header.get("reward"));
				
				if(header.containsKey("age")) {
					blockItem.put("age", formatDate((String)header.get("age")));
				}
				
				result.add(blockItem);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("getBlockInfo : " + e.getMessage());			
			}
			
			cnt++;
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
