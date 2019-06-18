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
* @ClassName: AnswerRecord.java
* @Description: 答题记录信息
* @version: v1.0.0
* @date: 2019年6月12日 下午4:09:16 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月12日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "答题记录信息")
public class AnswerRecord {
	
	@ApiModelProperty(dataType = "long",name = "answer_record_id",value = "答题记录id")
	private long answer_record_id; //答题记录id
	@ApiModelProperty(dataType = "String",name = "question_id",value = "题干id")
	private String question_id; //题干id
	@ApiModelProperty(dataType = "String",name = "submit_option",value = "提交选项")
	private String submit_option; //提交选项
	@ApiModelProperty(dataType = "int",name = "true_or_false",value = "正误")
	private int true_or_false; //正误
	
	@ApiModelProperty(dataType = "long",name = "user_id",value = "用户id")
	private long user_id; //用户id
	@ApiModelProperty(dataType = "String",name = "correct_option",value = "正确选项")
	private String correct_option; //正确选项
	@ApiModelProperty(dataType = "String",name = "answer_time",value = "答题时间")
	private String answer_time; //答题时间
	@ApiModelProperty(dataType = "String",name = "knowledge_area",value = "知识领域")
	private String knowledge_area; //知识领域
	
	@ApiModelProperty(dataType = "int",name = "score",value = "得分")
	private int score; //得分
}
