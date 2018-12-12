package com.tistory.needjarvis.web;


import javax.servlet.http.HttpServletRequest;

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
	private TransModule transModule;
	
	
	/**
	 * 트랜잭션 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/transaction-list", method = RequestMethod.GET)
	public String transactionList(Model model) {
		
		model.addAttribute("result", transModule.getTransList(false));
		
		return "transfer";
	}
	
	
	/**
	 * 코인 전송, temp/transfer 파일에 기록
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transfer(HttpServletRequest request, Model model) {
		String from = (String)request.getParameter("from");
		String to = (String)request.getParameter("to");
		String stlj = (String)request.getParameter("stlj");
		String memo = (String)request.getParameter("memo").replaceAll(",", " ");
		
		LOGGER.info(from + "=>" + to + " " + stlj);
		
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("stlj", stlj);
		model.addAttribute("memo", memo);
		
		// 코인 전송 
		model.addAttribute("success", transModule.transfer(from, to, stlj, memo));
				
		return "jsonView";
	}
}
