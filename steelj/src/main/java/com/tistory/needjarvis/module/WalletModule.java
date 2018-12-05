package com.tistory.needjarvis.module;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tistory.needjarvis.vo.AddressVO;
import com.tistory.needjarvis.vo.IDVO;


/**
 * 지갑에 관련된 모듈
 * 
 * @author jinhoo.jang
 * @since 2018.08.27
 */
@Component
public class WalletModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WalletModule.class);		
	
	
	/**
	 * IDVO의 정보를 JSON 형태로 변형한다
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toJSON(IDVO vo) {
		JSONObject item = new JSONObject();
		item.put("id", vo.getId());

		JSONArray addrArr = new JSONArray();
		JSONObject addr = new JSONObject();
		
		HashMap<String, AddressVO> map = vo.getAddressMap();
		for(String key : map.keySet()) {
			addr = new JSONObject();
			AddressVO addrVO = map.get(key);
			
			addr.put("address", key);
			addr.put("balance", addrVO.getBalance());
			addr.put("createDate", addrVO.getCreateDate());
			addr.put("lastDate", addrVO.getLastDate());
			addr.put("isLive", addrVO.isLive());
			
			addrArr.add(addr);
			addr = null;
		}
		
		item.put("addressMap", addrArr);
		return item.toJSONString();
	}
	
	
	/**
	 * 아이디 데이터를 IDVO로 변환
	 * @param str
	 * @return
	 */
	public IDVO setIDVO(String str) {
		LOGGER.info(str);
		
		IDVO vo = new IDVO();
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(str);
			JSONArray array = (JSONArray) obj.get("addressMap");
			vo.setId((String)obj.get("id"));
			
			if(array.size() > 0) {
				HashMap<String, AddressVO> map = new HashMap<String, AddressVO> ();
				AddressVO addrVO = new AddressVO();
				
				JSONObject item = (JSONObject) array.get(0);
				addrVO.setAddress((String)item.get("address"));
				addrVO.setBalance((String)item.get("balance"));
				addrVO.setCreateDate((String)item.get("createDate"));
				addrVO.setLastDate((String)item.get("lastDate"));
				addrVO.setLive((String)item.get("isLive"));
				
				map.put((String)item.get("address"), addrVO);
				vo.setAddressMap(map);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("setIDVO : " + e.getMessage());
			return null;
		}
		
		return vo; 
	}
}
