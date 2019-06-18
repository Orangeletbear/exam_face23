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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */
@Data
@ApiModel(description = "微信code和人脸照片")
public class CodeAndImage {
	
	@ApiModelProperty(dataType = "String",name = "code",value = "用户微信登录码")
	private String code; 	//微信登录码
	
	@ApiModelProperty(dataType = "String",name = "image",value = "用户人脸照片Base64编码")
	private String image; 	//人脸照片Base64编码
}
