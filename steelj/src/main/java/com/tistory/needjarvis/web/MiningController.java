package com.tistory.needjarvis.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * 블록을 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.12.04
 */
@Controller
public class MiningController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MiningController.class);
	
	private boolean miningFlag = false;
	
	
	/**
	 * 마이닝 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mining", method = RequestMethod.GET)
	public String mining(Model model) {
		
		model.addAttribute("isMining", miningFlag);
		
		// 마이닝의 레벨을 가져온다.
		
		return "mining";
	}
	
	/**
	 * 마이닝을 시작한다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mining/start", method = RequestMethod.GET)
	public String start(Model model) {
		LOGGER.info("start mine!!!");
		miningFlag = true;
		// 마이닝의 레벨을 가져온다.
		
		return "jsonView";
	}
	
	
	/**
	 * 마이닝을 시작한다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mining/status", method = RequestMethod.GET)
	public String status(Model model) {
		
		
		// 마이닝의 레벨을 가져온다.
		
		return "jsonView";
	}	
}
