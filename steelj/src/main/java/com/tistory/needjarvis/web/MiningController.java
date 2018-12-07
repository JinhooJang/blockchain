package com.tistory.needjarvis.web;


import java.util.HashMap;

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
import com.tistory.needjarvis.vo.AddressVO;
import com.tistory.needjarvis.vo.IDVO;


/**
 * 마이닝을 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.12.04
 */
@Controller
public class MiningController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MiningController.class);
	
	private boolean miningFlag = false;
	
	public String hashing;
	
	public MiningModule module = null; 
	
	@Autowired
	private CryptoModule cryptoModule;
	
	@Autowired
	private WalletService walletService;
	
	
	/**
	 * 마이닝 페이지 호출
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mining", method = RequestMethod.GET)
	public String mining(Model model) {
		
		model.addAttribute("isMining", miningFlag);
		
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
		
		// 마이닝을 수행한다.
		String no = cryptoModule.getHashed();
		String msg = "";
		
		IDVO vo = walletService.getIDInfo();
		HashMap<String, AddressVO> map = vo.getAddressMap();
		String address = "";
				
		for(String _address : map.keySet()) {
			address =  map.get(_address).getAddress();
			break;
		}
		
		if(address.trim().length() > 0) {
			module = new MiningModule(no, address);
			module.start();
		} else {
			msg = "address not found.";
		}
		
		model.addAttribute("isMining", miningFlag);
		model.addAttribute("msg", msg);
		
		return "jsonView";
	}
	
	
	/**
	 * 마이닝을 종료한다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mining/end", method = RequestMethod.GET)
	public String end(Model model) {
		LOGGER.info("finish mine!!!");
		miningFlag = false;
		
		// 마이닝에 종료 값을 전달한다.
		if(module.isAlive()) {
			module.endFlag();
		}
		
		model.addAttribute("isMining", miningFlag);		
		
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
		// 마이닝 상태값을 가져온다.
		// sequence, 블록당 채굴 시간
		
		return "jsonView";
	}	
}
