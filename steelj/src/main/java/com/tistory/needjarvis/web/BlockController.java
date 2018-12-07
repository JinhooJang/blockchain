package com.tistory.needjarvis.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tistory.needjarvis.module.CryptoModule;
import com.tistory.needjarvis.module.MiningModule;
import com.tistory.needjarvis.service.WalletService;



/**
 * 블록을 생성 및 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.12.07
 */
@Controller
public class BlockController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockController.class);
	
	private boolean miningFlag = false;
	
	public String hashing;
	
	@Autowired
	private CryptoModule cryptoModule;
	
	
	/**
	 * 블록 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/block", method = RequestMethod.GET)
	public String mining(Model model) {
		
		return "block";
	}
}
