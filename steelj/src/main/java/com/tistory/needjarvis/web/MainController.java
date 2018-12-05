package com.tistory.needjarvis.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tistory.needjarvis.service.PeerService;
import com.tistory.needjarvis.service.WalletService;
import com.tistory.needjarvis.vo.AddressVO;
import com.tistory.needjarvis.vo.IDVO;


/**
 * 피어를 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.08.10
 */
@Controller
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private WalletService walletService;
	
	
	/**
	 * 피어를 추가한다
	 * 
	 * @param addr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) {
		model.addAttribute("request", "main");
		
		return "main";
	}
	
	
	/**
	 * 로그인
	 * 
	 * @param addr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		String address = (String) request.getParameter("address");
		LOGGER.info("address=>" + address);
		
		if(address != null && address.length() > 0) {
			IDVO vo = walletService.getIDInfo();
			HashMap<String, AddressVO> map = vo.getAddressMap();
			boolean flag = false;
					
			for(String _address : map.keySet()) {
				if(address.equals(map.get(_address).getAddress())) {
					flag = true;
				}
				break;
			}
			
			LOGGER.info("flag=>" + flag);		
			if(flag) {
				return "redirect:main";
			}
		}
		
		return "login";
	}
	
	
	/**
	 * 월렛 및 계좌 생성
	 * 
	 * @param addr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model) {
		return "register";
	}
}
