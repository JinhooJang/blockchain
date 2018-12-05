package com.tistory.needjarvis.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tistory.needjarvis.module.CryptoModule;
import com.tistory.needjarvis.module.WalletModule;
import com.tistory.needjarvis.service.WalletService;
import com.tistory.needjarvis.vo.AddressVO;
import com.tistory.needjarvis.vo.IDVO;


/**
 * ID와 계좌를 관리하는 콘트롤러
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
@Controller
public class WalletController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);
		
	@Autowired
	private CryptoModule cryptoModule;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletModule walletModule;
	
	
	/**
	 * ID를 추가한다
	 * 
	 * @param addr
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/wallet/id", method = RequestMethod.GET)
	public String createId(HttpServletRequest request, Model model) throws Exception {
		// 랜덤 아이디를 새로 생성한다 (총 40자리)
		String id = cryptoModule.getRandomID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		// 서버에 해당 id값이 존재하는지 체크하는 메소드가 필요
		while(walletService.chkDuplicateId(id)) {
			// 중복일 경우, 아이디를 다시 생성
			id = cryptoModule.getRandomID();
		}
		
		if(walletService.chkFolder()) {
			IDVO vo = new IDVO();
			vo.setId(id);
			
			HashMap<String, AddressVO> map = new HashMap<String, AddressVO> ();
			AddressVO addrVO = new AddressVO();
			addrVO.setBalance(String.valueOf(0));
			addrVO.setCreateDate(sdf.format(new Date()));
			addrVO.setLastDate(sdf.format(new Date()));
			addrVO.setLive(String.valueOf(true));
			
			String address = cryptoModule.getRandomID();
			while(walletService.chkDuplicateAddr(address)) {
				
				// 중복일 경우, 계좌번호를 다시 생성
				address = cryptoModule.getRandomID();
			}
			
			addrVO.setAddress(address);
			LOGGER.debug("address=>" + address + " " + addrVO);
			map.put(address, addrVO);
			vo.setAddressMap(map);
			
			if(walletService.createAddress(walletModule.toJSON(vo))) {
				model.addAttribute("msg", "Account successfully created");
				model.addAttribute("account", addrVO);
			} else {
				model.addAttribute("msg", "failed to create account");
			}
			
		} else {
			model.addAttribute("msg", "Steelj folder is not exist. \n please, Create a folder first");
		}
		
		
		return "jsonView";
	}
	
	
	/**
	 * address를 보여준다
	 * 
	 * @param addr
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/wallet/get-address", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) throws Exception {

		IDVO vo = walletService.getIDInfo();
		HashMap<String, AddressVO> map = vo.getAddressMap();
				
		for(String address : map.keySet()) {
			model.addAttribute("address", map.get(address).getAddress());
			break;
		}
		
		return "jsonView";
	}
}
