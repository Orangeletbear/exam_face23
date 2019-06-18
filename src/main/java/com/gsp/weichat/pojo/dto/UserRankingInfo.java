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
package com.gsp.weichat.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: UserRankingInfo.java
* @Description: 排行榜信息，前端处理时间输出格式
* @version: v1.0.0
* @date: 2019年6月10日 下午8:03:20 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月10日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "用户排名汇总信息")
public class UserRankingInfo {
	
	@ApiModelProperty(dataType = "Long",name = "user_id",value = "用户id")
	private Long user_id; 			//用户id
	
	@ApiModelProperty(dataType = "String",name = "nickname",value = "用户昵称")
	private String nickname; 		//用户昵称
	
	@ApiModelProperty(dataType = "String",name = "head_portrait",value = "用户头像地址")
	private String head_portrait; 	//用户头像地址
	
	@ApiModelProperty(dataType = "String",name = "city",value = "用户城市")
	private String city; 			//用户城市
	
	@ApiModelProperty(dataType = "int",name = "total_score",value = "用户总分")
	private int total_score; 		//用户总分
	
	@ApiModelProperty(dataType = "int",name = "ranking",value = "用户排名")
	private int ranking; 			//用户排名
}
