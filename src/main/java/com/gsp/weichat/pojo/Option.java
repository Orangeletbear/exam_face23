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
* @ClassName: Option.java
* @Description: 选项信息汇总
* @version: v1.0.0
* @date: 2019年6月12日 下午4:16:06 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月12日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "选项信息")
public class Option {
	
	@ApiModelProperty(dataType = "long",name = "ID",value = "选项id")
	private long ID; //选项id
	@ApiModelProperty(dataType = "String",name = "OPTION_CONTENT",value = "选项内容")
	private String OPTION_CONTENT; //选项内容
	@ApiModelProperty(dataType = "String",name = "OPTION",value = "选项")
	private String OPTION; //选项
	@ApiModelProperty(dataType = "String",name = "QUESTION_ID",value = "题干id")
	private String QUESTION_ID; //题干id
	
	@ApiModelProperty(dataType = "String",name = "CREATED_DATE",value = "创建时间")
	private String 	CREATED_DATE; //创建时间
}
