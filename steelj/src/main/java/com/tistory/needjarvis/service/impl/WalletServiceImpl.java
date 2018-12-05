package com.tistory.needjarvis.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.needjarvis.module.CryptoModule;
import com.tistory.needjarvis.module.WalletModule;
import com.tistory.needjarvis.service.WalletService;
import com.tistory.needjarvis.vo.IDVO;


/**
 * 지갑 서비스
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
@Service
public class WalletServiceImpl implements WalletService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);
	
	@Autowired
	private CryptoModule cryptoModule;
	
	@Autowired
	private WalletModule walletModule;
	
	
	/** 
	 * 파일을 읽어와, ID와 계좌 정보를 가져온다
	 * @return
	 */
	@Override
	public IDVO getIDInfo() {
		IDVO vo = null;
		BufferedReader inFiles = null;
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream("c:/steelj/wallet/id"), "UTF8"));
			
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					vo = walletModule.setIDVO(line);	
					LOGGER.info("vo=>" + vo);
				}
			}
			
			inFiles.close();							
		} catch (Exception e) {
			LOGGER.error("createAddress : " + e.getMessage());
			return null;
		}
		
		return vo;
	}

	
	/**
	 * 계좌를 생성한다
	 * @param id
	 * @param address
	 * @return
	 */
	@Override
	public boolean createAddress(String str) {
		BufferedWriter bw = null;
		
		// 파일을 생성
		try {
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/wallet/id", false),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
			bw.write(str);
			bw.close();
		}catch(IOException e){
			LOGGER.error("createAddress : " + e.getMessage());
			return false;
		}		
		
		return true;
	}

	
	/**
	 * 해당 아이디가 중복되었는지 체크한다
	 * @param id
	 * @return
	 */
	@Override
	public boolean chkDuplicateId(String id) {
		// 여기에 체크 로직이 들어가야 함. 중복이면 트루 전송
		return false;
	}


	/**
	 * 해당 주소가 중복되었는지 체크한다
	 * @param addr
	 * @return
	 */
	@Override
	public boolean chkDuplicateAddr(String addr) {
		// 여기에 체크 로직이 들어가야 함. 중복이면 트루 전송
		return false;
	}


	/**
	 * 작업 대상이 될 폴더가 존재하는지 체크한다
	 * @return
	 */
	@Override
	public boolean chkFolder() {		
		File f = new File("C:/steelj");
		if(f.exists()) {
			File walFolder = new File(f.getAbsolutePath() + "/wallet");
			
			// 지갑 폴더가 없으면 자동적으로 생성한다
			if(!walFolder.exists()) {
				LOGGER.info("Wallet does not exist. Create a folder");
				walFolder.mkdirs();
			}
			
			return true;
		} else {
			LOGGER.error("Steelj folder is not exist. \n please, Create a folder first");
			return false;
		} 
	}
}
