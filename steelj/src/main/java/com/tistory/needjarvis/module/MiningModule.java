package com.tistory.needjarvis.module;

import java.util.HashMap;


/**
 * 마이닝을 수행하는 모듈
 * 
 * @author Jinhoo Jang
 * @since 2018.12.05
 */
public class MiningModule extends Thread {
	private String no;
	private String addr;
	private CryptoModule crypto;
	private boolean executeFlag = true;
	
	public MiningModule(String no, String addr) {
		this.no = no;
		this.addr = addr;
		crypto = new CryptoModule();
	}
	
	public void run() {
		int blockSeq = Integer.parseInt(no);
		
		// 정답을 위한 해시작업
		while (executeFlag) {
			long startTime = System.currentTimeMillis();
			
			// 예전 블록의 정보를 가져온다			
			HashMap<String, Object> bfInfo = crypto.getBlockInfo(String.valueOf(blockSeq));			
			HashMap<String, String> map = mining((String)bfInfo.get("hashed"));
			
			long endTime = System.currentTimeMillis();
			blockSeq++;
			
			// 블록 생성
			crypto.setBlockJson(map, blockSeq, (endTime-startTime));
			
			// 시퀀스값 기록
			crypto.setSequence(blockSeq);
		}
    }
	
	
	/**
	 * 마이닝
	 */
	public HashMap<String, String> mining(String hashed) {
		HashMap<String, String> map = new HashMap<String, String>(); 
		boolean flag = true;
				
		String word = "";
		String merge = "";		
		 	
		map = new HashMap<String, String>(); 
			
		do {
			try {
				word = crypto.generateRandomWord(30);
				merge = crypto.sha256(hashed + word);
				flag = crypto.isCorrect(merge); 
				
				if(flag) {
					System.out.println(merge + " " + executeFlag);
				}
				
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(!flag && executeFlag);
		
		
		map.put("word", word);
		map.put("merge", merge);
		map.put("hashed", merge);
		map.put("reward", "5");
		map.put("miner", addr);
		map.put("age", crypto.getDate());
		
		return map;
	}
	
	
	/**
	 * 플래그값을 종료로 변환한다.
	 */
	public void endFlag() {
		executeFlag = false;
	}
}
