package com.tistory.needjarvis.web;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tistory.needjarvis.service.JobService;


/**
 * 구직 활동 컨트롤러
 * 
 * @author jinhoo.jang
 * @since 2018.12.14
 */
@Controller
public class JobSearchController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobSearchController.class);
	
	@Autowired
	private JobService jobService;
	
	
	/**
	 * 구직자 생성 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/job-seeker", method = RequestMethod.GET)
	public String jobSeeker(Model model) {
		
		// 구직자 리스트 호출
		model.addAttribute("result", jobService.getJobSeekerList());
		
		return "job-seeker";
	}
	
	
	/**
	 * 구직자 생성 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/job-seeker/create", method = RequestMethod.GET)
	public String jobSeekerCreate(HttpServletRequest request, Model model) {
		String name = (String)request.getParameter("name");
		
		LOGGER.info("name=>" + name);
		
		// 구직자 생성
		model.addAttribute("success", jobService.addJobSeeker(name));
		
		return "jsonView";
	}
	
	
	/**
	 * 회사 생성 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String company(Model model) {
		
		// 구직자 리스트 호출
		model.addAttribute("result", jobService.getCompanyList());
		
		return "company";
	}
	
	
	/**
	 * 회사 생성 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/create", method = RequestMethod.GET)
	public String companyCreate(HttpServletRequest request, Model model) {
		
		String name = (String)request.getParameter("name");
		
		LOGGER.info("name=>" + name);
		
		// 구직자 생성
		model.addAttribute("success", jobService.addCompany(name));
		
		return "jsonView";
	}
	
	
	/**
	 * 전체 구직 활동 리스트
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/job-search", method = RequestMethod.GET)
	public String jobSearch(HttpServletRequest request, Model model) {
				
		// 구직 활동 리스트
		//model.addAttribute("result", jobService.getCompanyList());
		
		model.addAttribute("jobSeeker", jobService.getJobSeekerList());
		model.addAttribute("company", jobService.getCompanyList());
		
		return "job-search";
	}
	
	/**
	 * 구직자 및 회사 리스트를 가져온다(select box 용)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show-address", method = RequestMethod.GET)
	public String showAddress(HttpServletRequest request, Model model) {
		
		String address = (String)request.getParameter("address");
		
		// 구직 활동 리스트
		model.addAttribute("result", jobService.getShowAddress(address));
		model.addAttribute("address", address);
		
		return "show-address";
	}
	
	
	/**
	 * 구직 활동
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/job-search/create", method = RequestMethod.GET)
	public String jobSearchCreate(HttpServletRequest request, Model model) {
		
		String from = (String)request.getParameter("from");
		String to = (String)request.getParameter("to");
		String action = (String)request.getParameter("action");
		
		LOGGER.info("from=>" + from + " to=>" + to + " action=>" + action);
		
		// 구직자 생성
		model.addAttribute("success", jobService.addJobSearch(from, to, action));
		
		return "jsonView";
	}
}
