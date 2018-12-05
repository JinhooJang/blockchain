package com.tistory.needjarvis.web;

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


/**
 * 피어를 관리하는 클래스
 * 
 * @author jinhoo.jang
 * @since 2018.08.10
 */
@Controller
public class PeerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerController.class);
	
	@Autowired
	private PeerService peerService;
	
	
	/**
	 * 피어를 추가한다
	 * 
	 * @param addr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/peer/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		String addr = request.getRemoteAddr();
		LOGGER.info(addr);
		model.addAttribute("status", peerService.addPeer(addr));
		model.addAttribute("request", "add peer");
		return "jsonView";
	}
	
	
	/**
	 * 피어를 삭제한다
	 * 
	 * @param addr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/peer/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("addr") String addr, Model model) {
		
		// 피어가 살아 있을 경우, 삭제
		if(!peerService.callLive(addr))
			model.addAttribute("status", peerService.deletePeer(addr));
		
		model.addAttribute("request", "delete peer");
		return "jsonView";
	}
	
	
	/**
	 * 피어 리스트를 보여준다
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/peer/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", peerService.getListPeer());
		model.addAttribute("request", "get peer list");
		return "jsonView";
	}
}
