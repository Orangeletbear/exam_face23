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

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */

@Data
@ApiModel(description = "题目信息")
public class Question {
	
	@ApiModelProperty(dataType = "String",name = "ID",value = "题干id")
	private String ID; //题干id
	@ApiModelProperty(dataType = "String",name = "CONTENT",value = "题干内容")
	private String CONTENT; //题干内容
	@ApiModelProperty(dataType = "String",name = "ANSWER",value = "正确选项")
	private String ANSWER; //正确选项
	@ApiModelProperty(dataType = "String",name = "KNOWLEDGE_AREA",value = "知识领域")
	private String KNOWLEDGE_AREA; //知识领域
	
	@ApiModelProperty(dataType = "String",name = "CREATED_DATE",value = "创建时间")
	private String CREATED_DATE; //创建时间
	@ApiModelProperty(dataType = "List<Option>",name = "options",value = "选项")
	private List<Option> options; //选项
}
