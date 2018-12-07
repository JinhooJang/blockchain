package com.tistory.needjarvis.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tistory.needjarvis.module.BlockModule;
import com.tistory.needjarvis.module.CryptoModule;
import com.tistory.needjarvis.module.TransModule;



/**
 * 블록을 생성 및 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.12.07
 */
@Controller
public class TransactionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private CryptoModule cryptoModule;
	
	@Autowired
	private TransModule transModule;
	
	
	/**
	 * 트랜잭션 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transfer(Model model) {
		
		model.addAttribute("result", transModule.getTransList(false));
		
		return "transfer";
	}
}
