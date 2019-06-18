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
package com.gsp.weichat.core.service.service_impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.aip.face.AipFace;
import com.gsp.weichat.common.Constant;
import com.gsp.weichat.common.util.face_util.AipFaceFactory;
import com.gsp.weichat.common.util.face_util.FaceQualityInspection;
import com.gsp.weichat.common.util.face_util.FaceSearch;
import com.gsp.weichat.common.util.weichat_util.WeiChatUtil;
import com.gsp.weichat.core.mapper.UserMapper;
import com.gsp.weichat.core.service.UserService;
import com.gsp.weichat.pojo.User;
import com.gsp.weichat.pojo.dto.CollectInfo;
/*
 * @ApiOperation和@ApiParam为添加的API相关注解，个参数说明如下：
 *@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, 
 *response = “接口返回参数类型”, notes = “接口发布说明”；其他参数可参考源码；
 *@ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”
 * Copyright: Copyright (c) 2019 ChongYang
 * @ClassName: UserController.java
 * @Description: 用户信息收集
 * @version: v1.0.0
 * @date: 2019年5月30日 上午10:51:07 
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2019年5月30日     YancyChong           v1.0.0               修改原因
 */
@Service
@Transactional(transactionManager = "transactionManager")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${setting.weichat.appId}")
	private String appId;
	
	@Value("${setting.weichat.AppSecret}")
	private String secret;
	
	/**
	 * 用户注册
	 */
	@Override
	public String regist(User user) {
		//验证照片是否合格
		String baidu_token = new FaceQualityInspection().isPass(user.getImage());
		if(baidu_token.equals(Constant.FAIL)) {
			return Constant.FAIL;
		}
		//使用工具获得openId
		WeiChatUtil weiChatUtil = new WeiChatUtil();
		String openId = weiChatUtil.getOpenIdAndSession_key(user.getCode(), appId, secret).get("openId");
		
		//查看该人脸是否已经注册过
		if(new FaceSearch().search(user.getImage(), "exam_face") != null) {
			return Constant.FAIL;
		}else {
			AipFace aipFace = AipFaceFactory.getAipFace();
			HashMap<String, String> options = new HashMap<>();
			options.put("user_info", "人脸识别智能刷题系统用户");
			JSONObject jst = aipFace.addUser(user.getImage(), "BASE64", "exam_face", baidu_token, options);
			if(jst.getDouble("error_code") != 0) {
				throw new RuntimeException("人脸注册入百度智能云失败");
			}
		}
		
		//存储User数据
		user.setBaidu_token(baidu_token);
		user.setCreate_time(new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date()));
		user.setOpen_id(openId);
		try {
			userMapper.saveUserInfo(user);
		} catch (Exception e) {
			AipFace aipFace = AipFaceFactory.getAipFace();
			aipFace.deleteUser("exam_face", baidu_token, new HashMap<String, String>());
			e.printStackTrace();
			throw new RuntimeException("存储用户信息失败");
		}
		
		return Constant.SUCCESS;
	}
	
	
	/**
	 * 检测用户是否已经注册
	 */
	@Transactional(readOnly = true)
	@Override
	public String isRegist(String code) {
		
		//发送get请求获得微信数据
		WeiChatUtil weiChatUtil = new WeiChatUtil();
		String openId = weiChatUtil.getOpenIdAndSession_key(code, appId, secret).get("openId");
		
		//查询openId是否已注册
		User user = userMapper.findUserByOpenId(openId);
		if(user != null) {
			return Constant.SUCCESS;
		}
		return Constant.FAIL;
	}
	
	/**
	 * 用户登录
	 */
	@Transactional(readOnly = true)
	@Override
	public String login(String code, String image) {
		//发送get请求获得微信数据
		WeiChatUtil weiChatUtil = new WeiChatUtil();
		String openId = weiChatUtil.getOpenIdAndSession_key(code, appId, secret).get("openId");
		
		//查询openId是否已注册
		User user = userMapper.findUserByOpenId(openId);
		
		if(user != null) {
			//验证照片是否合格
			String baidu_token = new FaceQualityInspection().isPass(image);
			if(baidu_token.equals(Constant.FAIL)) {
				return Constant.FAIL;
			}
			
			//验证人脸信息
			JSONObject faceInfo = new FaceSearch().search(image, "exam_face", user.getBaidu_token());
			
			if(faceInfo != null) {
				long user_id = user.getUser_id();
				return Constant.SUCCESS + "&" + user_id;
			}
		}
		return Constant.FAIL;
	}
	
	/**
	 * 根据用户id 查询用户答题汇总信息
	 */
	@Transactional(readOnly = true)
	@Override
	public CollectInfo getCollectInfo(long user_id) {
		CollectInfo collectInfo = userMapper.getConllectInfo(user_id);
		if(null == collectInfo) {
			return new CollectInfo();
		} else {
			return collectInfo;
		}
	}

}
