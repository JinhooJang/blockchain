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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
 * @since 2018.10.23
 */
@Component
public class CryptoModule {

	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoModule.class);
	
	private final String[] hdCols = {"hashed","reward","miner"};
	private final String[] transCols = {"from","to","stlj","memo"};
	
	private HashMap<String, Integer> balanceMap = new HashMap<String, Integer> ();
	
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
     * 결과 체크, 난이도
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
    			if(hex2Decimal(String.valueOf(str.charAt(i))) < 3) {
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
	 * 제네시스 블록으로 초기화를 수행한다
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean setGenesisBlock() {
		BufferedReader inFiles;
		BufferedWriter bw;
		
		StringBuffer sb = new StringBuffer();
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream("c:/steelj/genesis.json"), "UTF8"));
			
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					sb.append(line.toString());
				}
			}
			
			inFiles.close();
		} catch (Exception e) {
			LOGGER.error("get genesis : " + e.getMessage());
			return false;
		}
		
		// 파일을 생성
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/chain/00000", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(sb.toString());
			JSONObject block = new JSONObject();
			
			// 헤더 세팅
			block.put("header", genesisHeader((JSONObject)obj.get("header")));
			// 전송 세팅
			block.put("transfer", genesisTransfer((JSONObject)obj.get("transfer")));
			// 렛저 세팅
			block.put("ledger", genesisLedger((JSONObject)obj.get("transfer")));
						
			bw.write(block.toJSONString());
			bw.close();			
			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/chain/sequence", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			bw.write("00000");
			bw.close();
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.error("set genesis : " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 맵 데이터를 JSON 구조로 변경한다
	 * 
	 * @param map
	 * @return
	 */
	public boolean setBlockJson(HashMap<String, String> map, 
			int blockSeq, long miningTime) {
		
		JSONObject obj = new JSONObject();
		JSONObject headerObj = new JSONObject();
		BufferedWriter bw;
		
		// 헤드값 세팅
		headerObj.put("word", (String)map.get("word"));
		headerObj.put("hashed", (String)map.get("hashed"));
		headerObj.put("reward", (String)map.get("reward"));
		headerObj.put("age", (String)map.get("age"));
		
		String miner = "";
		
		// 5초안에 채굴 못하면, 마스터 계정이 코인 보상 받음
		if(miningTime > 5000) {
			miner = "0000000000000000000000000000000000000000";
		} else {
			miner = (String)map.get("miner");
		}
		
		// 5초안에 채굴 못하면, 마스터 계정이 코인 보상 받음
		headerObj.put("miner", miner);
		
		obj.put("header", headerObj);
		
		// transfer 저장
		obj.put("transfer", setTransfer((String)map.get("reward"),miner));
		
		// ledger 저장
		obj.put("ledger", setLedger(miner));
		
		// 파일을 생성
		String blockNo = "";
		
		// 귀찮아서 임시 하드 코딩, 나중에 for문으로 변경 필요, 파일명을 5자리로 고정시킨다
		if(blockSeq < 10) {
			blockNo = "0000" + blockSeq;
		} else if(blockSeq < 100) {
			blockNo = "000" + blockSeq;
		} else if(blockSeq < 1000) {
			blockNo = "00" + blockSeq;
		} else if(blockSeq < 10000) {
			blockNo = "0" + blockSeq;
		}				
		
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/chain/" + blockNo, false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			bw.write(obj.toJSONString());
			bw.close();						
			
		} catch(Exception e){
			LOGGER.error("setBlockJson : " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 시퀀스 기록
	 * @param blockSeq
	 */
	public void setSequence(int blockSeq) {
		BufferedWriter bw;
		
		// 시퀀스 기록
		try {	
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/chain/sequence", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			// 귀찮아서 임시 하드 코딩, 나중에 for문으로 변경 필요, 파일명을 5자리로 고정시킨다
			if(blockSeq < 10) {
				bw.write("0000" + blockSeq);
			} else if (blockSeq < 100) {
				bw.write("000" + blockSeq);
			} else if (blockSeq < 1000) {
				bw.write("00" + blockSeq);
			} else if (blockSeq < 10000) {
				bw.write("0" + blockSeq);
			}
			
			bw.close();
		} catch(Exception e){
			LOGGER.error("setSequence : " + e.getMessage());
		}
	}
	
	
	/**
	 * 헤드에는 hash, reward, miner 가 기록되어 있어야 함
	 * 
	 * @param header
	 * @return
	 */
	public HashMap<String, String> genesisHeader(JSONObject header) {
		HashMap<String, String> rtnMap = new HashMap<String, String> ();
		
		for(String col : hdCols) {
			rtnMap.put(col, (String)header.get(col));
		}
		
		// 날짜 추가
		rtnMap.put("age", getDate());
		
		return rtnMap;		
	}
	
	
	/**
	 * 전송에는 from, to, balance, memo가 있어야 함
	 * 
	 * @param tranfer
	 * @return
	 */
	public HashMap<String, String> genesisTransfer(JSONObject tranfer) {
		HashMap<String, String> rtnMap = new HashMap<String, String> ();
		
		for(String col : transCols) {
			rtnMap.put(col, (String)tranfer.get(col));
		}
		
		return rtnMap;		
	}
	
	
	/**
	 * 최초 블록을 읽어서 Ledger를 생성한다
	 * 
	 * @param ledger
	 * @return
	 */
	public List<HashMap<String, String>> genesisLedger(JSONObject ledger) {
		List<HashMap<String, String>> list 
						= new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> item = new HashMap<String, String> ();
		
		// stlj를 from에서 제거하고, to에 증가시켜야 된다. from이 void일 경우, 제거를 시키지 않음.
		int stlj = Integer.parseInt((String)ledger.get("stlj"));
		String from = (String)ledger.get("from");
		String to = (String)ledger.get("to");
		int amount = 0;
		
		// from에서 제거한다, void일 경우 무시한다
		if(!from.equals("void")) {
			// balance가 있으면 값을 가져온다
			if(balanceMap.containsKey(from)) {
				amount = balanceMap.get(from) - stlj;
				balanceMap.put(from, amount);
				item.put("address", from);
				item.put("balance", String.valueOf(amount));
				list.add(item);
			}
		}
		
		amount = 0;
		item = new HashMap<String, String> ();
		// to에서 증가시킨다.
		if(balanceMap.containsKey(to)) {
			amount = balanceMap.get(to) + stlj;
		} else {
			amount = stlj;
		}
		
		balanceMap.put(to, amount);
		item.put("address", to);
		item.put("balance", String.valueOf(amount));
		list.add(item);
						
		return list;
	}
	
	
	/**
	 * 장부의 내용을 세팅과 동시에 보상도 거래로 기록
	 * 
	 * @param reward
	 * @param miner
	 * @return
	 */
	public HashMap<String, String> setTransfer(String reward, String miner) {
		HashMap<String, String> rtnMap = new HashMap<String, String> ();
		
		// 우선 mining을 기록
		rtnMap.put("from", "void");
		rtnMap.put("to", miner);
		rtnMap.put("stlj", reward);
		rtnMap.put("memo","채굴 보상");
		
		// to의 주소의 발란스를 증가시킨다
		int balance = 0;
		if(balanceMap.containsKey(miner)) {
			balance = balanceMap.get(miner) + Integer.parseInt(reward);
		} else {
			balance = Integer.parseInt(reward);
		}
		LOGGER.info(miner + "=>" + balance);
		balanceMap.put(miner, balance);
		
		return rtnMap;		 
	}
	
	
	/**
	 * Ledger를 생성한다
	 * 
	 * @param ledger
	 * @return
	 */
	public List<HashMap<String, String>> setLedger(String miner) {
		List<HashMap<String, String>> list 
						= new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> item;
		
		// 전체 값을 세팅
		for(String key : balanceMap.keySet()) {
			item = new HashMap<String, String> ();
			
			item.put("address", key);
			item.put("balance", String.valueOf(balanceMap.get(key)));
			list.add(item);
			
			item = null;
		}
						
		return list;
	}
	
	
	/**
     * 예전 블록의 정보를 가져온다
     * 블록이 없다면, Genesis 블록의 정보를 가져온다
     * 
     * @param string[] hashed,chainNo
     */
	public HashMap<String, Object> getBlockInfo(String no) {
		HashMap<String, Object> map = new HashMap<String, Object> ();
		BufferedReader inFiles = null;
		StringBuffer sb = new StringBuffer();
		String blockNo = "";
		
		// 귀찮아서 임시 하드 코딩, 나중에 for문으로 변경 필요, 파일명을 5자리로 고정시킨다
		if(Integer.parseInt(no) < 10) {
			blockNo = "0000" + Integer.parseInt(no);
		} else if(Integer.parseInt(no) < 100) {
			blockNo = "000" + Integer.parseInt(no);
		} else if(Integer.parseInt(no) < 1000) {
			blockNo = "00" + Integer.parseInt(no);
		} else if(Integer.parseInt(no) < 10000) {
			blockNo = "0" + Integer.parseInt(no);
		}
					
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream(
							"c:/steelj/chain/" + blockNo), "UTF8"));
			
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
			JSONArray ledger = (JSONArray) obj.get("ledger");
			
			for(String col : hdCols) {
				map.put(col, header.get(col));
			}
			
			// 발란스 값도 세팅
			setBalance(ledger);
			
		} catch (Exception e) {
			LOGGER.error("getBlockInfo : " + e.getMessage());			
		}		
		
		return map;
	}
	
	
	/**
     * Hash 값을 가져온다
     * 
     * @param map
     */
	public String getHashed() {
		BufferedReader inFiles = null;
		String no = "";
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream("c:/steelj/chain/sequence"), "UTF8"));
			
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					no = line.trim();
				}
			}
			
			inFiles.close();							
		} catch (Exception e) {
			LOGGER.error("getHashed : " + e.getMessage());
			return null;
		}
		
		return no;
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
	public String generateRandomWord(int wordLength) {
	    Random r = new Random();
	    StringBuilder sb = new StringBuilder(wordLength);
	    
	    for(int i = 0; i < wordLength; i++) {
	        char tmp = (char) ('a' + r.nextInt('z' - 'a'));
	        sb.append(tmp);
	    }
	    
	    return sb.toString();
	}
	
	
	/**
	 * 날짜값을 호출한다
	 * @return
	 */
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	
	/**
	 * 계좌의 잔고를 메모리에 로드한다
	 */
	public void setBalance(JSONArray list) {
		LOGGER.info("size => " + list.size());
		
		// 마지막 블록의 정보를 읽는다.
		for(int i = 0; i < list.size(); i++) {
			JSONObject item = (JSONObject) list.get(i);
			
			LOGGER.info("address===>" + item.get("address"));
			LOGGER.info("balance===>" + item.get("balance"));
			
			balanceMap.put((String)item.get("address"), 
					Integer.parseInt((String)item.get("balance")));			
		}
	}
}
