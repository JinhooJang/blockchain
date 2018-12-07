package com.tistory.needjarvis.module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CryptoModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoModule.class);
	
	/**
     * SHA-256으로 해싱하는 메소드
     * 
     * @param bytes
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public String sha256(String msg) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        
        return bytesToHex(md.digest());
    }
 
 
    /**
     * 바이트를 헥스값으로 변환한다
     * 
     * @param bytes
     * @return
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
          builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
    
    /**
     * 40자리 랜덤 값을 리턴한다
     * 
     * @param size
     * @return
     */
    public String getRandomID() {
    	String id = UUID.randomUUID().toString().replaceAll("-", "");
		id += UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		
		return id;
    }
    
    
    /**
     * 결과 체크
     *  
     * @param str
     * @return
     */
    public boolean isCorrect(String str) {
    	int len = 2;
    	
    	// 0.1초마다 요청을 하기 때문에...
    	for(int i = 0; i < len; i++) {
    		// 마지막 값
    		if(i == len-1) {
    			if(hex2Decimal(String.valueOf(str.charAt(i))) < 5) {
    				return true;
    			}
    		} 
    		// 0 이 아니면 false
    		else {
    			if(hex2Decimal(String.valueOf(str.charAt(i))) > 0) {
    				return false;
    			}
    		}    		    		
    	}
    	
    	return false;
    }
    
    
    /**
     * 검증을 위한 임시 블록 세팅
     * 
     * @param map
     */
	public void setTempBlock(HashMap<String, String> map) {
		BufferedWriter bw;
		StringBuffer sb = new StringBuffer();
		
		// hashed,word,merge
		sb.append(map.get("hashed"));
		sb.append("," + map.get("word"));
		sb.append("," + map.get("merge"));
		
		// 파일을 생성
		try {
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/temp/block", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			bw.write(sb.toString());
			bw.close();
		}catch(IOException e){
			LOGGER.error("setTempBlock : " + e.getMessage());
		}	
	}
	
	
	/**
     * Hash 값을 가져온다
     * 
     * @param map
     */
	public String getHashed() {
		BufferedReader inFiles = null;
		String hashed = "";
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream("c:/steelj/wallet/id"), "UTF8"));
			
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					hashed = "";
				}
			}
			
			inFiles.close();							
		} catch (Exception e) {
			LOGGER.error("getHashed : " + e.getMessage());
			return null;
		}
		
		return hashed;
	}
	
	
	/**
     * 블록 생성 메소드 (JSON 구조)
     * 
     * @param map
     */
	public void setValidBlock(HashMap<String, String> map) {
		BufferedWriter bw;
		StringBuffer sb = new StringBuffer();
		
		// hashed,word,merge
		sb.append(map.get("hashed"));
		sb.append("," + map.get("word"));
		sb.append("," + map.get("merge"));
		
		// 파일을 생성
		try {
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/temp/block", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			bw.write(sb.toString());
			bw.close();
		}catch(IOException e){
			LOGGER.error("setTempBlock : " + e.getMessage());
		}	
	}
	
	
	/**
	 * 맵 데이터를 JSON 구조로 변경한다
	 * 
	 * @param map
	 * @return
	 */
	public String setBlockJson(HashMap<String, String> map) {
		JSONObject obj = new JSONObject();
		JSONObject headerObj = new JSONObject();
		
		headerObj.put("hashed", (String)map.get("hashed"));
		headerObj.put("word", (String)map.get("word"));
		headerObj.put("merge", (String)map.get("merge"));
		
		obj.put("header", headerObj);
		
		return obj.toJSONString();
	}
	
	
	/**
	 * Hex 값을 Decimal로 변환
	 * @param s
	 * @return
	 */
	public int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
	
	
	/**
	 * 랜덤 워드
	 * 
	 * @param wordLength
	 * @return
	 */
	String generateRandomWord(int wordLength) {
	    Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
	    StringBuilder sb = new StringBuilder(wordLength);
	    for(int i = 0; i < wordLength; i++) { // For each letter in the word
	        char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
	        sb.append(tmp); // Add it to the String
	    }
	    return sb.toString();
	}
}
