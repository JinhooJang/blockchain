package com.tistory.needjarvis.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tistory.needjarvis.module.HolderModule;


/**
 * 홀더들의 정보를 보여 준다
 * 
 * @author jinhoo.jang
 * @since 2018.12.07
 */
@Controller
public class HolderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HolderController.class);
	
	@Autowired
	private HolderModule holderModule;
	
	
	/**
	 * 트랜잭션 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/holder", method = RequestMethod.GET)
	public String transfer(Model model) {
		
		model.addAttribute("result", holderModule.getHolderList());
		
		return "holder";
	}
}
