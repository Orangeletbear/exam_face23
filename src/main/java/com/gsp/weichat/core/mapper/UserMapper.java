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
package com.gsp.weichat.core.mapper;


import org.apache.ibatis.annotations.Param;

import com.gsp.weichat.pojo.User;
import com.gsp.weichat.pojo.dto.CollectInfo;
/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: UserMapper.java
* @Description: 根据用户IP查询用户答题汇总信息
* @version: v1.0.0
* @date: 2019年5月31日 上午9:44:05 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月31日     YancyChong           v1.0.0               修改原因
 */
public interface UserMapper {

	/**
	 * 存储用户信息
	 * @param user
	 */
	void saveUserInfo(User user);
	/**
	 * 根据openId查询用户信息
	 * @param openId
	 * @return
	 */
	User findUserByOpenId(@Param("openId")String openId);

	/**
	 * 根据用户id 查询用户答题汇总信息
	 * @param user_id
	 * @return
	 */
	CollectInfo getConllectInfo(@Param("user_id")long user_id);
	
}
