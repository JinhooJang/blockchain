package com.tistory.needjarvis.module;

import java.security.MessageDigest;
import java.util.UUID;

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
}
