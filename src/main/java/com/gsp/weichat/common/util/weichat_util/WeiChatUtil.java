/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gsp.weichat.common.util.weichat_util;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gsp.weichat.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */

@Slf4j
public class WeiChatUtil {
	public Map<String, String> getOpenIdAndSession_key(String code, String appId, String secret) {
		HttpUtil httpUtil = new HttpUtil();
		//发送get请求获得微信数据
		String uri = "https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid=" + appId + "&secret=" + secret + "&js_code=" + code
				+ "&grant_type=authorization_code";
		
		//获得数据
		String jsonData = (String) httpUtil.getRequest(uri);
		String openId = null;
		String session_key = null;
		System.out.println(jsonData);
		log.info("openid获取json数据：{}", jsonData);
		//解析数据
		try {
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = jsonParser.parse(jsonData).getAsJsonObject();
			session_key = jsonObject.get("session_key").getAsString();
			openId = jsonObject.get("openid").getAsString();
		} catch (Exception e) {
			System.out.println(jsonData);
			e.printStackTrace();
			throw new RuntimeException("微信小程序解析openid或session_key出错");
		}
		Map<String, String> result = new HashMap<>();
		result.put("openId", openId);
		result.put("sesstion_key", session_key);
		return result;
	}
}
