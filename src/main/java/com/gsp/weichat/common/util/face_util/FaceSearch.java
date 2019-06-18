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
package com.gsp.weichat.common.util.face_util;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */
public class FaceSearch {
	/**
	 * 
	* @Function: FaceSearch.java
	* @Description: 跟据提供的人脸照片的Base64编码
	*去百度云服务搜索人脸
	* @param: imageBase64 照片的编码
	* @param group 在哪个人脸组搜索
	* @return：JSONObject 搜索到的人脸json数据
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: YancyChong
	* @date: 2019年6月12日 下午4:00:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月12日     YancyChong           v1.0.0               修改原因
	 */
	public JSONObject search(String imageBase64, String groupList, String baidu_token) {
		//SDK客户端
		AipFace aipFace = AipFaceFactory.getAipFace();
		HashMap<String, String> options = new HashMap<>();

		//活体检测要求
		options.put("liveness_control", "NORMAL");
		//人脸认证
		options.put("user_id", baidu_token);
		
		JSONObject jsonObject = aipFace.search(imageBase64, "BASE64", groupList, options);
		if(jsonObject.getDouble("error_code") != 0 || 
		   jsonObject.getJSONObject("result").getJSONArray("user_list")
		   .getJSONObject(0).getDouble("score") < 80){
			return null;
		}
		return jsonObject;
	}
	
	
	public JSONObject search(String imageBase64, String groupList) {
		//SDK客户端
		AipFace aipFace = AipFaceFactory.getAipFace();
		HashMap<String, String> options = new HashMap<>();
		
		//活体检测要求
		options.put("liveness_control", "NORMAL");
		//人脸认证
		
		JSONObject jsonObject = aipFace.search(imageBase64, "BASE64", groupList, options);
		if(jsonObject.getDouble("error_code") != 0 || 
				jsonObject.getJSONObject("result").getJSONArray("user_list")
				.getJSONObject(0).getDouble("score") < 80){
			return null;
		}
		return jsonObject;
	}
}
