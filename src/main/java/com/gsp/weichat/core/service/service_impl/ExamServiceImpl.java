/*
66 9... * Copyright 2012-2017 the original author or authors.
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
package com.gsp.weichat.core.service.service_impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsp.weichat.common.Constant;
import com.gsp.weichat.core.mapper.ExamMapper;
import com.gsp.weichat.core.service.ExamService;
import com.gsp.weichat.pojo.AnswerDay;
import com.gsp.weichat.pojo.AnswerRecord;
import com.gsp.weichat.pojo.Option;
import com.gsp.weichat.pojo.Question;
import com.gsp.weichat.pojo.dto.UserRankingInfo;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */

@Service
@Transactional(transactionManager = "transactionManager")
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	private ExamMapper examMapper;
	
	/**
	 * 
	* @see com.gsp.weichat.core.service.ExamService#randomQuestion()  
	* @Function: ExamServiceImpl.java
	* @Description: 获取一个试题
	* @param:试题描述
	* @return：返回结果描述
	* @throws：异常描述
	* @version: v1.0.0
	* @date: 2019年6月12日 下午3:58:34 
	 */
	@Transactional(readOnly = true)//数据库自动读取数据信息
	@Override
	public Question randomQuestion() {
		//查询题目总数
		int questionTotal = examMapper.getQuestionTotal();
		
		//生成随机数
		int index = new Random().nextInt(questionTotal);
		
		//随机查询question对象
		Question question = examMapper.randomQuestion(index);
		
		if(null == question) {
			throw new RuntimeException("随机查询题库失败");//运行期异常抛出
		}else {
			//去掉题干内容的标签
			List<Option> options = question.getOptions();
			for(int i = 0; i < options.size(); i ++) {
				Option option = options.get(i);
				option.setOPTION_CONTENT(option.getOPTION_CONTENT().split("：")[1]);
			}
			return question;
		}
	}
	
	/**
	 * 
	* @see com.gsp.weichat.core.service.ExamService#answer(com.gsp.weichat.pojo.AnswerRecord)  
	* @Function: ExamServiceImpl.java
	* @Description: 用户答题更新数据
	* @param:answerRecord
	* @return：是否成功，成功success失败fail
	* @throws：异常描述
	* @version: v1.0.0
	* @date: 2019年6月12日 下午3:57:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月12日     YancyChong           v1.0.0               修改原因
	 */
	@Override
	public String answer(AnswerRecord answerRecord) {
		//设置时间
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		answerRecord.setAnswer_time(nowDate);
		
		//生成记录
		examMapper.record(answerRecord);
		System.out.println(answerRecord.toString());
		
		//查询每日记录表
		Long answer_statistics_day_id = examMapper.findAnswerDayIdByUserIdAndTime(answerRecord.getUser_id(),
				new SimpleDateFormat("yyyy-MM-dd").format(date));
		if(null == answer_statistics_day_id) {
			examMapper.newAnswerDay(answerRecord.getUser_id());
		}
		answer_statistics_day_id = examMapper.findAnswerDayIdByUserIdAndTime(answerRecord.getUser_id(),
				new SimpleDateFormat("yyyy-MM-dd").format(date));
		
		//查询汇总表
		Long answer_collect_id = examMapper.findAnswerCollectIdByUserId(answerRecord.getUser_id());
		if(null == answer_collect_id) {
			examMapper.newAnswerCollect(answerRecord.getUser_id());
		}
		answer_collect_id = examMapper.findAnswerCollectIdByUserId(answerRecord.getUser_id());
		
		//更新表
		if(answerRecord.getTrue_or_false() == 1) {
			//正确处理
			examMapper.AnswerDayTrue(answer_statistics_day_id.longValue());
			examMapper.AnswerCollectTrue(answer_collect_id.longValue());
		}else if(answerRecord.getTrue_or_false() == 0) {
			//错误处理
			examMapper.AnswerDayFalse(answer_statistics_day_id.longValue());
			examMapper.AnswerCollectFalse(answer_collect_id.longValue());
		}
		
		return Constant.SUCCESS;
	}
	
	/**
	 * 查询前50名用户
	 */
	@Transactional(readOnly = true)
	@Override
	public List<UserRankingInfo> getTop50() {
		List<UserRankingInfo> top50 = examMapper.getTop50();
		if(null == top50) {
			throw new RuntimeException("没查询到数据");
		}else {
			return top50;
		}
	}
	
	/**
	 * 查询用户近几次答题记录
	 */
	@Transactional(readOnly = true)
	@Override
	public List<AnswerDay> answerDayRecord(Long user_id) {
		List<AnswerDay> answerDays = examMapper.answerDay(user_id);
		
		if(answerDays.size() > 0 && null != answerDays.get(0)) {
			//查询用户总分
			int totalScore = examMapper.findScoreByUserId(user_id);
			for (int index = 0; index < answerDays.size(); index ++) {
				answerDays.get(index).setPile_score(totalScore);
				totalScore -= answerDays.get(index).getTotal_scroe_day();
			}
		}
		return answerDays;
	}

}
