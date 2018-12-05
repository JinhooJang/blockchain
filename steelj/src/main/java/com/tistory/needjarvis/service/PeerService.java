package com.tistory.needjarvis.service;

import java.util.HashMap;

/**
 * 피어(노드)를 관리하기 위한 서비스
 * 
 * @author jinhoo.jang
 * @since 2018.08.10
 */
public interface PeerService {
	
	/**
	 * 피어 추가
	 * @param addr
	 */
	public boolean addPeer(String addr);
	
	
	/**
	 * 피어 삭제
	 * @param addr
	 */
	public boolean deletePeer(String addr);
	
	
	/**
	 * 피어 상태 확인 
	 * @param addr
	 * @return
	 */
	public boolean callLive(String addr);
	
	
	/**
	 * 피어 리스트
	 * @return
	 */
	public HashMap<String, String> getListPeer();
}