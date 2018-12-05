package com.tistory.needjarvis.vo;

import java.util.HashMap;


/**
 * ID와 계좌 정보를 담는 Value Object
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
public class IDVO {
	/** 노드 아이디 */
	private String id;
	/** 계좌 Value Object */
	private HashMap<String, AddressVO> addressMap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, AddressVO> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(HashMap<String, AddressVO> addressMap) {
		this.addressMap = addressMap;
	}
}
