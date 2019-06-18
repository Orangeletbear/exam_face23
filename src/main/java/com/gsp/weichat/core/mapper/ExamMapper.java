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
package com.gsp.weichat.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gsp.weichat.pojo.AnswerDay;
import com.gsp.weichat.pojo.AnswerRecord;
import com.gsp.weichat.pojo.Question;
import com.gsp.weichat.pojo.dto.UserRankingInfo;

/**
 * 
* Copyright: Copyright (c) 2019 ChongYang
* @ClassName: ExamMapper.java
* @Description: list集合前五十名数据库的人员信息
* @version: v1.0.0
* @date: 2019年6月12日 下午4:17:55 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月12日     YancyChong           v1.0.0               修改原因
 */
@Mapper
public interface ExamMapper {

	/**
	 * 查询数据库一共有多少条题干数据
	 * @return
	 */
	@Select("select count(*) from question")
	int getQuestionTotal();

	/**
	 * 随机查询一道题
	 * @param index
	 * @return
	 */
	Question randomQuestion(@Param("index")int index);

	/**
	 * 生成答题记录
	 * @param answerRecord
	 */
	void record(AnswerRecord answerRecord);

	/**
	 * 查询每日记录
	 * @param user_id
	 * @param format
	 * @return	记录id
	 */
	Long findAnswerDayIdByUserIdAndTime(@Param("user_id")long user_id, @Param("time")String time);

	/**
	 * 创建每日记录
	 * @param user_id
	 */
	void newAnswerDay(@Param("user_id")long user_id);

	/**
	 * 查询汇总表id
	 * @param user_id
	 * @return
	 */
	Long findAnswerCollectIdByUserId(@Param("user_id")long user_id);

	/**
	 * 创建汇总表
	 * @param user_id
	 */
	void newAnswerCollect(@Param("user_id")long user_id);

	/**
	 * 答题正确更新每日答题表
	 * @param longValue
	 */
	void AnswerDayTrue(@Param("table_id")long table_id);

	/**
	 * 答题正确更新汇总表
	 * @param longValue
	 */
	void AnswerCollectTrue(@Param("table_id")long table_id);

	/**
	 * 答题错误更新每日答题表
	 * @param longValue
	 */
	void AnswerDayFalse(@Param("table_id")long longValue);

	/**
	 * 答题错误更新汇总表
	 * @param longValue
	 */
	void AnswerCollectFalse(@Param("table_id")long table_id);

	/**
	 * 查询前50名用户信息
	 * @return
	 */
	List<UserRankingInfo> getTop50();
	//将 50 条用户数据分别映射入 UserRankingInfo对象并放入 list 容器

	/**
	 * 查询用户近几次答题记录
	 * @param user_id
	 * @return
	 */
	List<AnswerDay> answerDay(@Param("user_id") Long user_id);

	/**
	 * 根据用户id查询用户的总分
	 * @param user_id
	 * @return
	 */
	int findScoreByUserId(@Param("user_id") Long user_id);

}
