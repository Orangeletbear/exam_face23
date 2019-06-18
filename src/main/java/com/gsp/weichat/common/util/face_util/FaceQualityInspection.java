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
import com.gsp.weichat.common.Constant;

/**
 * 人脸照片质量检测
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */
public class FaceQualityInspection {
	
	/**
	 * 人脸照片检测
	 * @param imageBase64 照片Base64编码
	 * @return	baidu_token
	 */
	public String isPass(String imageBase64) {
		AipFace aipFace = AipFaceFactory.getAipFace();
		//质量检测
		HashMap<String, String> options = new HashMap<>();
		options.put("face_field", "quality");
		JSONObject jsonObject = aipFace.detect(imageBase64, "BASE64", options);
		if(jsonObject.getDouble("error_code") != 0) {
			return Constant.FAIL;
		}
		JSONObject jsonRuslt_quanlity = jsonObject.getJSONObject("result").getJSONArray("face_list")
				.getJSONObject(0).getJSONObject("quality");
		JSONObject jsonRuslt_angle = jsonObject.getJSONObject("result").getJSONArray("face_list")
				.getJSONObject(0).getJSONObject("angle");
		
		try {
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("left_eye") < 0.6)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("right_eye") < 0.6)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("nose") < 0.7)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("mouth") < 0.7)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("left_cheek") < 0.8)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("right_cheek") < 0.8)
			if (jsonRuslt_quanlity.getJSONObject("occlusion").getDouble("chin_contour") < 0.6)
			if (jsonRuslt_quanlity.getDouble("blur") < 0.7)
			if (jsonRuslt_quanlity.getDouble("illumination") > 30)
			if (jsonRuslt_quanlity.getInt("completeness") == 1)
			if (jsonRuslt_angle.getDouble("yaw") < 20 && jsonRuslt_angle.getDouble("yaw") > -20)
			if (jsonRuslt_angle.getDouble("pitch") < 20 && jsonRuslt_angle.getDouble("pitch") > -20)
			if (jsonRuslt_angle.getDouble("roll") < 20 && jsonRuslt_angle.getDouble("roll") > -20) {
				return jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0)
						.getString("face_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("人连照片质量检测出现异常");
		}
		return Constant.FAIL;
	}
}
