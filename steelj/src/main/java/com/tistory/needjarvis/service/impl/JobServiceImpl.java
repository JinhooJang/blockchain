package com.tistory.needjarvis.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.needjarvis.module.CryptoModule;
import com.tistory.needjarvis.module.HolderModule;
import com.tistory.needjarvis.service.JobService;


/**
 * 직업 관련 서비스
 * 
 * @author Jinhoo Jang
 * @since 2018.12.14
 */
@Service
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
	
	final String NEWLINE = System.getProperty("line.separator");
	
	@Autowired
	private CryptoModule cryptoModule;
	
	
	/**
	 * 사용자 추가, csv 형태로 저장
	 * 
	 * @param name
	 * @param grade
	 */
	@Override
	public boolean addJobSeeker(String name) {
		BufferedWriter bw;
		
		// 사용자를 추가한다
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/job/job-seeker-list", true),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
						
			bw.write(name + "," + cryptoModule.sha256(name) + NEWLINE);
			bw.close();
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.error("addJobSeeker : " + e.getMessage());
			return false;
		}
		
		return true;
	}

	
	/**
	 * 구직자 리스트
	 * 
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> getJobSeekerList() {
		List<HashMap<String, Object>> rtnList 
					= new ArrayList<HashMap<String, Object>> ();
		
		BufferedReader inFiles;
		String[] temp = null;
		HashMap<String, Object> map;
		
		try {
			inFiles = new BufferedReader(
					new InputStreamReader(
					new FileInputStream("c:/steelj/job/job-seeker-list"), "UTF8"));
			
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					map = new HashMap<String, Object> ();
					temp = line.split(",");
					
					map.put("name", temp[0]);
					map.put("address", temp[1]);
					
					rtnList.add(map);
				}
			}
			
			inFiles.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("get genesis : " + e.getMessage());			
		}
		
		return rtnList;
	}


	@Override
	public boolean addCompany(String name) {
		BufferedWriter bw;
		
		// 사용자를 추가한다
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/job/company-list", true),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
						
			bw.write(name + "," + cryptoModule.sha256(name) + NEWLINE);
			bw.close();
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.error("addJobSeeker : " + e.getMessage());
			return false;
		}
		
		return true;
	}


	@Override
	public List<HashMap<String, Object>> getCompanyList() {
		List<HashMap<String, Object>> rtnList 
							= new ArrayList<HashMap<String, Object>> ();
		
		BufferedReader inFiles;
		String[] temp = null;
		HashMap<String, Object> map;
		
		try {
			inFiles = new BufferedReader(
				new InputStreamReader(
				new FileInputStream("c:/steelj/job/company-list"), "UTF8"));
		
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					map = new HashMap<String, Object> ();
					temp = line.split(",");
					
					map.put("name", temp[0]);
					map.put("address", temp[1]);
					
					rtnList.add(map);
				}
			}
		
			inFiles.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("get genesis : " + e.getMessage());			
		}
		
		return rtnList;
	}


	@Override
	public boolean addJobSearch(String from, String to, String action) {
		BufferedWriter bw;
		
		// 사용자를 추가한다
		try {			
			bw = new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(
						"c:/steelj/job/job-search", true),	// true to append 
						StandardCharsets.UTF_8));	// set encoding utf-8
			
						
			bw.write(from + "," + to + "," + action + NEWLINE);
			bw.close();
		} catch(Exception e){
			e.printStackTrace();
			LOGGER.error("addJobSearch : " + e.getMessage());
			return false;
		}
		
		return true;
	}


	@Override
	public List<HashMap<String, Object>> getShowAddress(String address) {

		List<HashMap<String, Object>> rtnList 
							= new ArrayList<HashMap<String, Object>> ();
		
		BufferedReader inFiles;
		String[] temp = null;
		HashMap<String, Object> map;
		
		try {
			inFiles = new BufferedReader(
				new InputStreamReader(
				new FileInputStream("c:/steelj/job/job-search"), "UTF8"));
		
			String line = "";
			while((line = inFiles.readLine()) != null) {
				if(line.trim().length() > 0) {
					map = new HashMap<String, Object> ();
					temp = line.split(",");
					
					if(temp[0].equals(address) || temp[1].equals(address)) {
					
						// 인터뷰는 from과 to가 변환
						if("interview".equals(temp[2])) {
							map.put("from", temp[1]);
							map.put("to", temp[0]);
							map.put("action", temp[2]);
						} else {
							map.put("from", temp[0]);
							map.put("to", temp[1]);
							map.put("action", temp[2]);
						}
						
						rtnList.add(map);
					}
				}
			}
		
			inFiles.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("getJobSearchList : " + e.getMessage());			
		}
		
		return rtnList;
	}
}
