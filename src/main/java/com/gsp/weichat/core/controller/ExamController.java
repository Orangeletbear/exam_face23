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
package com.gsp.weichat.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsp.weichat.common.Constant;
import com.gsp.weichat.core.service.ExamService;
import com.gsp.weichat.pojo.AnswerDay;
import com.gsp.weichat.pojo.AnswerRecord;
import com.gsp.weichat.pojo.Question;
import com.gsp.weichat.pojo.dto.UserRankingInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: ExamController.java
* @Description: 该类的功能描述
* @version: v1.0.0
* @date: 2019年6月11日 下午4:22:41 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月11日     YancyChong           v1.0.0               修改原因
 */
@Api(tags = "答题相关API")
@RestController
@RequestMapping("/v1/exam_face/exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	
	@GetMapping("/getQuestion")
	@ApiOperation(value = "随机获得一道题及其选项信息", notes = "<p>作者：LCY<p>")
	@ApiResponses(@ApiResponse(code = 200, message = "返回题目信息Question"))
	public Question getQuestion() {
		Question q = examService.randomQuestion();
		return q;
	}
	
	
	@PostMapping("/answer")
	@ApiOperation(value = "答题记录上传，上传答题记录进行总数据更新", notes = "<p>作者：LCY<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "answerRecord", value = "答题记录对象json数据",
				required = true, paramType = "body", dataType = "AnswerRecord" ,
				defaultValue = "无")
	})
	@ApiResponses(@ApiResponse(code = 200, message = "成功success,失败返回fail"))
	public String answer(@RequestBody AnswerRecord answerRecord) {
		String result = Constant.FAIL;
		if(null != answerRecord) {
			result = examService.answer(answerRecord);
		}else {
			throw new RuntimeException("参数错误");
		}
		return result;
	}
	
	
	@GetMapping("/getTop50")
	@ApiOperation(value = "获取前50名用户排名信息", notes = "<p>作者：LCY<p>")
	@ApiResponses(@ApiResponse(code = 200, message = "返回List<UserRankingInfo>"))
	public List<UserRankingInfo> getTop50(){
		return examService.getTop50();
	}
	
	
	@GetMapping("/answerDayRecord")
	@ApiOperation(value = "根据用户id查询用户近5次的，每日答题记录", notes = "<p>作者：GongShaopeng<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "user_id", value = "用户id",
				required = true, paramType = "query", dataType = "Long" ,
				defaultValue = "无")
	})
	@ApiResponses(@ApiResponse(code = 200, message = "成功List<AnswerDay>"))
	public List<AnswerDay> answerDayRecord(Long user_id){
		if(null != user_id) {
			return examService.answerDayRecord(user_id);
		}else {
			throw new RuntimeException("参数user_id不能为空");
		}
	}
}
