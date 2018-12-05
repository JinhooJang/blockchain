package com.tistory.needjarvis.vo;

import java.util.Date;

/**
 * Address Value Object
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
public class AddressVO {
	
	/** 계좌 번호 */
	private String address;
	/** 잔고 */
	private String balance;
	/** 계좌 생성일 */
	private String createDate;
	/** 최종 수정일 */
	private String lastDate;
	/** 계좌 활성화 여부 */
	private String isLive;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String isLive() {
		return isLive;
	}
	public void setLive(String isLive) {
		this.isLive = isLive;
	} 
}
