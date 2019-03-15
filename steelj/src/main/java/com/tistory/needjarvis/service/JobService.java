package com.tistory.needjarvis.service;

import java.util.HashMap;
import java.util.List;

/**
 * 직업 관련 서비스
 * 
 * @author Jinhoo Jang
 * @since 2018.12.14
 */
public interface JobService {

	/**
	 * 사용자 추가
	 * @param name
	 */
	public boolean addJobSeeker(String name);
	

	/**
	 * 구직자 리스트
	 * @return
	 */
	public List<HashMap<String, Object>> getJobSeekerList();
	
	
	/**
	 * 회사 추가
	 * @param name
	 */
	public boolean addCompany(String name);
	

	/**
	 * 회사 리스트
	 * @return
	 */
	public List<HashMap<String, Object>> getCompanyList();
	
	
	/**
	 * 구직 활동
	 * @param from
	 * @param to
	 * @param compNm
	 * @param action
	 * @return
	 */
	public boolean addJobSearch(String from, String to, String action);
	
	
	/**
	 * 해당 주소와 관련된 트랜잭션을 보여준다
	 * @param address
	 * @return
	 */
	public List<HashMap<String, Object>> getShowAddress(String address);
}