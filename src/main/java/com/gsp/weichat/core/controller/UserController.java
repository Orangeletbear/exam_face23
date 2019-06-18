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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsp.weichat.core.service.UserService;
import com.gsp.weichat.pojo.User;
import com.gsp.weichat.pojo.dto.CodeAndImage;
import com.gsp.weichat.pojo.dto.CollectInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v3.0
 */

@Api(tags = "关于用户信息的相关操作")
@RestController
@RequestMapping("/v1/exam_face/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/regist")
	@ApiOperation(value = "注册用户信息", notes = "<p>作者：GongShaopeng<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "user", value = "code:小程序前端调用wx.login获得的code</br>"
				+ "image:用户面部照片的Base64编码",
				required = true, paramType = "body", dataType = "User" ,
				defaultValue = "无")
	})
	@ApiResponses(@ApiResponse(code = 200, message = "注册成功返回success,失败返回fail"))
	public String regist(@RequestBody User user) {
		//返回结果
		String result;
		if(null != user.getCode() && !"".equals(user.getCode()) && 
		   null != user.getImage() && !"".equals(user.getImage())) {
			
			result = userService.regist(user);
		}else {
			throw new RuntimeException("没有获取到微信code");
		}
		
		return result;
	}
	
	
	
	@GetMapping("/isRe gist")
	@ApiOperation(value = "查看用户是已经注册", notes = "<p>作者：GongShaopeng<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "code:小程序前端调用wx.login获得的code</br>",
				required = true, paramType = "query", dataType = "String" ,
				defaultValue = "无")
	})
	@ApiResponses(@ApiResponse(code = 200, message = "已注册返回yes，未注册返回no"))
	public String isRegist(String code) {
		String result = userService.isRegist(code);
		if(result.equals("success")) {
			return "yes";
		}else {
			return "no";
		}
	}
	
	
	//登录
	@ApiOperation(value = "用户登录API", notes = "<p>作者：GongShaopeng<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "codeAndImage", value = "code:小程序前端调用wx.login获得的code</br>" 
				+ "image:用户面部照片的Base64编码",
				required = true, paramType = "body", dataType = "CodeAndImage" ,
				defaultValue = "无")
	})
	@ApiResponses(@ApiResponse(code = 200, message = "登录成功返回(success&user_id),失败返回fail"))
	@PostMapping("/login")
	public String login(@RequestBody CodeAndImage codeAndImage) {
		//返回结果
		String result;
		if(null != codeAndImage.getCode() && !"".equals(codeAndImage.getCode()) && 
		   null != codeAndImage.getImage() && !"".equals(codeAndImage.getImage())) {
			
			result = userService.login(codeAndImage.getCode(), codeAndImage.getImage());
		}else {
			throw new RuntimeException("没有获取到微信code");
		}
		
		return result;
	}
	
	
	
	/**
	 *因为用户信息数据经常会用到，所以在第一次查询后，将数据在前端缓存放一份，
	 *这样当下次需要用到数据时可以直接在前端获取。
	 *注意： 当用户信息数据发生改变时需要同时更新前后两端的数据信息。
	 */
	@ApiOperation(value = "获取用户汇总信息", notes = "<p>作者：LCY<p>")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "user_id", value = "用户id</br>",
				required = true, paramType = "query", dataType = "long" ,
				defaultValue = "无")
	})
	/**@ApiImplicitParam的paramType：query、form域中的值需要使用@RequestParam获取， 
	 * header域中的值需要使用@RequestHeader来获取，path域中的值需要使用@PathVariable来获取，
	 * body域中的值使用@RequestBody来获取，否则可能出错；
	 * 而且如果paramType是body，name就不能是body，否则有问题，
	 */
	@ApiResponses(@ApiResponse(code = 200, message = "成功CollectInfo,失败返回null"))
	@GetMapping("/getCollectInfo")
	public CollectInfo getCollectInfo(Long user_id) {
		if(null != user_id) {
			return userService.getCollectInfo(user_id.longValue());
		}else {
			return null;
		}
	}
}
