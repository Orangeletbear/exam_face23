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
package com.gsp.weichat.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: User.java
* @Description: 用户信息
* @version: v1.0.0
* @date: 2019年6月1日 下午1:42:46 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月1日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "用户信息汇总")
public class User {
	
	@ApiModelProperty(dataType = "long",name = "user_id",value = "用户id")
	private long user_id; //用户id
	@ApiModelProperty(dataType = "String",name = "open_id",value = "用户微信open_id")
	private String open_id; //用户微信open_id
	@ApiModelProperty(dataType = "String",name = "create_time",value = "用户注册时间")
	private String create_time; //注册时间
	@ApiModelProperty(dataType = "String",name = "baidu_token",value = "百度人脸识别唯一标识")
	private String baidu_token; //百度人脸识别唯一标识
	
	@ApiModelProperty(dataType = "String",name = "head_portrait",value = "用户头像")
	private String head_portrait; //用户头像
	@ApiModelProperty(dataType = "String",name = "nickname",value = "用户名称")
	private String nickname; //用户名称
	@ApiModelProperty(dataType = "String",name = "country",value = "国家")
	private String country; //国家
	@ApiModelProperty(dataType = "String",name = "province",value = "省份")
	private String province; //省份
	
	@ApiModelProperty(dataType = "String",name = "city",value = "城市")
	private String city; //城市
	@ApiModelProperty(dataType = "String",name = "code",value = "用户微信登录码")
	private String code; //用户微信登录码
	@ApiModelProperty(dataType = "String",name = "image",value = "用户人脸像")
	private String image; //用户人脸像
	
}
