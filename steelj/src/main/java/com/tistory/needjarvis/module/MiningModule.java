package com.tistory.needjarvis.module;

import java.util.HashMap;


/**
 * 마이닝을 수행하는 모듈
 * 
 * @author Jinhoo Jang
 * @since 2018.12.05
 */
public class MiningModule extends Thread {
	private String hashed;
	private CryptoModule crypto;
	private boolean executeFlag = true;
	
	public MiningModule(String hashed) {
		this.hashed = hashed;
		crypto = new CryptoModule();
	}
	
	public void run() {
		int blockSeq = 0;
		// 정답을 위한 해시작업
		while (executeFlag) {
			// 예전 블록의 정보를 가져온다
			
			
			HashMap<String, String> map = mining();
			
			System.out.println("block json=> " + crypto.setBlockJson(map));
			// 채굴이 되었다면 기록, 일정이상 시간이 걸렸으면 마스터 계정에 보낸다
		}
    }
	
	
	/**
	 * 마이닝
	 */
	public HashMap<String, String> mining() {
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
				
				System.out.println(merge + "=>" + crypto.isCorrect(merge) + " " + executeFlag);				
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(!flag && executeFlag);
		
		map.put("word", word);
		map.put("merge", merge);
		map.put("hashed", hashed);
		
		return map;
	}
	
	
	/**
	 * 플래그값을 종료로 변환한다.
	 */
	public void endFlag() {
		executeFlag = false;
	}
}
