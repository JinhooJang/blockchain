package com.tistory.needjarvis.service;

import com.tistory.needjarvis.vo.IDVO;

/**
 * 지갑 서비스
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
public interface WalletService {
	
	/** 
	 * 파일을 읽어와, ID와 계좌 정보를 가져온다
	 * @return
	 */
	public IDVO getIDInfo();
	
	
	/**
	 * 계좌를 생성한다
	 * @param id
	 * @param address
	 * @return
	 */
	public boolean createAddress(String str);
	
	
	/**
	 * 해당 아이디가 중복되었는지 체크한다
	 * @param id
	 * @return
	 */
	public boolean chkDuplicateId(String id);
	
	
	/**
	 * 해당 주소가 중복되었는지 체크한다
	 * @param addr
	 * @return
	 */
	public boolean chkDuplicateAddr(String addr);
	
	
	/**
	 * 작업 대상이 될 폴더가 존재하는지 체크한다
	 * @return
	 */
	public boolean chkFolder();
}
