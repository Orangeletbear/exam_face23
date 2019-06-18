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
* @ClassName: AnswerDay.java
* @Description: 每日答题记录信息汇总
* @version: v1.0.0
* @date: 2019年6月10日 下午4:08:45 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月12日     YancyChong           v1.0.0               修改原因
 */
@Data
@ApiModel(description = "每日答题记录信息")
public class AnswerDay {
	
	@ApiModelProperty(dataType = "Long",name = "answer_statistics_day_id",value = "每日答题记录id")
	private Long answer_statistics_day_id; //每日答题记录id
	@ApiModelProperty(dataType = "Long",name = "user_id",value = "用户id")
	private Long user_id; //用户id
	@ApiModelProperty(dataType = "long",name = "answer_total_true",value = "答对题总数")
	private long answer_total_true; //答对题总数
	@ApiModelProperty(dataType = "long",name = "answer_total_false",value = "答错题总数")
	private long answer_total_false; //答错题总数
	
	@ApiModelProperty(dataType = "long",name = "answer_total",value = "答题总数")
	private long answer_total; //答题总数
	@ApiModelProperty(dataType = "long",name = "total_scroe_day",value = "今日总分")
	private long total_scroe_day; //今日总分
	@ApiModelProperty(dataType = "long",name = "answer_time",value = "答题时间")
	private String answer_time; //答题时间
	@ApiModelProperty(dataType = "long",name = "pile_score",value = "当天累积得分")
	private long pile_score;
}
