package com.tistory.needjarvis.module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
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
public class TransModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransModule.class);

	final String NEWLINE = System.getProperty("line.separator");
	
	/**
     * 최근의 트랜잭션 정보를 리스트 형태로 가져온다
     * 
     * @param  
     */
	public List<HashMap<String, String>> getTransList(boolean isMain) {
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
				JSONArray transfer = (JSONArray) obj.get("transfer");
				JSONObject header = (JSONObject) obj.get("header");
				
				for(int i = 0; i < transfer.size(); i++) {
					JSONObject transfObj = (JSONObject) transfer.get(i);
					blockItem = new HashMap<String, String> ();
					
					blockItem.put("from", (String)transfObj.get("from"));
					blockItem.put("to", (String)transfObj.get("to"));
					blockItem.put("stlj", (String)transfObj.get("stlj"));
					blockItem.put("memo", (String)transfObj.get("memo"));
					
					if(header.containsKey("age")) {
						blockItem.put("age", formatDate((String)header.get("age")));
					}
					
					result.add(blockItem);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("getBlockInfo : " + e.getMessage());			
			}
			
			cnt++;
		}		
		
		return result;
	}
	
	
	/**
	 * 코인을 전송하는 메소드, 블록이 아닌 temp(csv 형태)에 우선 기록하고
	 * 다음 블록이 생성될 경우 적합성 여부를 확인 한 후 블록에 최종 기록
	 * 
	 * @param from
	 * @param to
	 * @param stlj
	 * @param memo
	 * @return
	 */
	public boolean transfer(String from, String to, String stlj, String memo) {
		BufferedWriter bw;
		
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/temp/transfer", true),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			// 그냥 단순히 기록
			bw.write(from + "," + to + "," + stlj + "," + memo + NEWLINE);
			bw.close();
			
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.error("transfer : " + e.getMessage());
			return false;
		}
		
		return true;
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
