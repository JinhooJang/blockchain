package com.tistory.needjarvis.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.needjarvis.module.PeerModule;
import com.tistory.needjarvis.service.PeerService;
import com.tistory.needjarvis.web.PeerController;


/**
 * 피어 서비스
 * 
 * @author jinhoo.jang
 * @since 2018.08.10
 */
@Service
public class PeerServiceImpl implements PeerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerServiceImpl.class);
	
	@Autowired
	private PeerModule peerModule;
	
	private HashMap<String, String> peerList = new LinkedHashMap<String, String> (); 

	@Override
	public boolean addPeer(String addr) {
		if(!peerList.containsKey(addr)) {
			peerList.put(addr, "");
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deletePeer(String addr) {
		if(peerList.containsKey(addr)) {
			peerList.remove(addr);
			return true;
		}
		
		return false;
	}

	@Override
	public HashMap<String, String> getListPeer() {
		return peerList;
	}

	@Override
	public boolean callLive(String addr) {
		return peerModule.callLive(addr);
	}
}
