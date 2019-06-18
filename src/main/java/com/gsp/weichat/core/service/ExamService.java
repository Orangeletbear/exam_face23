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
package com.gsp.weichat.core.service;

import java.util.List;

import com.gsp.weichat.pojo.AnswerDay;
import com.gsp.weichat.pojo.AnswerRecord;
import com.gsp.weichat.pojo.Question;
import com.gsp.weichat.pojo.dto.UserRankingInfo;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */
public interface ExamService {

	/**
	 * 随机获得一个试题
	 * @return	试题对象
	 */
	Question randomQuestion();

	/**
	 * 用户答题更新数据
	 * @param answerRecord
	 * @return 是否成功，成功success失败fail
	 */
	String answer(AnswerRecord answerRecord);

	/**
	 * 查询前50名信息
	 * @return
	 */
	List<UserRankingInfo> getTop50();

	/**
	 * 查询用户近几次答题记录
	 * @param user_id
	 * @return
	 */
	List<AnswerDay> answerDayRecord(Long user_id);
	
	
}
