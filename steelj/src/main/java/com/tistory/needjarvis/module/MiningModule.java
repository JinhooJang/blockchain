package com.tistory.needjarvis.module;

import java.util.HashMap;
import java.util.Random;


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
		// 정답을 위한 해시작업
		HashMap<String, String> map = mining();
		
		// 정답을 블록에 쌓기전에, 파일에 내린다.
		crypto.setTempBlock(map);
    }
	
	
	/**
	 * 마이닝
	 */
	public HashMap<String, String> mining() {
		HashMap<String, String> map = new HashMap<String, String>(); 
		boolean flag = true;
				
		String word = "";
		String merge = "";		
		 	
		while (executeFlag) {
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
			
			System.out.println("block json=> " + crypto.setBlockJson(map));
			
			map = null;
		}
		
		return map;
	}
	
	
	/**
	 * 플래그값을 종료로 변환한다.
	 */
	public void endFlag() {
		executeFlag = false;
	}
}
