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

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: CollectInfo.java
* @Description: 用户汇总信息，前端验证数据处理时间输出格式
* @version: v1.0.0
* @date: 2019年6月3日 上午10:42:55 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月3日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "用户汇总信息")
public class CollectInfo {
	
	@ApiModelProperty(dataType = "long",name = "user_id",value = "用户id")
	private long user_id; 			//用户id
	
	@ApiModelProperty(dataType = "String",name = "nickname",value = "用户昵称")
	private String nickname; 		//用户昵称
	
	@ApiModelProperty(dataType = "String",name = "head_portrait",value = "用户头像")
	private String head_portrait; 	//用户头像
	
	@ApiModelProperty(dataType = "long",name = "answer_collect_id",value = "答题汇总id")
	private long answer_collect_id; //答题汇总id
	
	@ApiModelProperty(dataType = "long",name = "total_score",value = "总分")
	private long total_score; 		//总分
	
	@ApiModelProperty(dataType = "long",name = "answer_total_true",value = "正确总数")
	private long answer_total_true; //正确总数
	
	@ApiModelProperty(dataType = "long",name = "answer_total_false",value = "错误总数")
	private long answer_total_false;//错误总数
	
	@ApiModelProperty(dataType = "long",name = "answer_total",value = "答题总数")
	private long answer_total;		//答题总数
	
	@ApiModelProperty(dataType = "int",name = "ranking",value = "排名")
	private int ranking; 			//排名
	
	@ApiModelProperty(dataType = "List<String>",name = "area",value = "知识领域")
	private List<String> area; 		//知识领域
}
