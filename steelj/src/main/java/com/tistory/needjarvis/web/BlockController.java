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



/**
 * 블록을 생성 및 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.12.07
 */
@Controller
public class BlockController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockController.class);
	
	@Autowired
	private CryptoModule cryptoModule;
	
	@Autowired
	private BlockModule blockModule;
	
	
	/**
	 * 블록 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/block", method = RequestMethod.GET)
	public String block(Model model) {
		
		model.addAttribute("result", blockModule.getBlockList(false));
		
		return "block";
	}
	
	
	/**
	 * 블록 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/block/genesis", method = RequestMethod.GET)
	public String genesis(Model model) {
		boolean genesis = cryptoModule.setGenesisBlock();
		model.addAttribute("genesis", genesis);
		
		return "jsonView";
	}
}
