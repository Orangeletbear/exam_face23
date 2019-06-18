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
package com.gsp.weichat.core.service;

import com.gsp.weichat.pojo.User;
import com.gsp.weichat.pojo.dto.CollectInfo;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */
public interface UserService {
	
	/**
	 * 用户注册接口
	 * @param code <p>用户的微信code</p>
	 * @param image  <p>用户照片信息</p>
	 * @return String 成功或失败信息
	 */
	String regist(User user);

	
	/**
	 * 检测用户是否已注册
	 * @param code	<p>用户的微信code</p>
	 * @return	String 成功或失败信息
	 */
	String isRegist(String code);

	
	/**
	 * 用户登录
	 * @param code	<p>用户的微信code</p>
	 * @param image  <p>用户照片信息</p>
	 * @return	String 成功或失败信息
	 */
	String login(String code, String image);

	
	/**
	 * 根据用户id 查询用户答题汇总信息
	 * @param user_id
	 * @return CollectInfo 答题汇总信息
	 */
	CollectInfo getCollectInfo(long user_id);
}
