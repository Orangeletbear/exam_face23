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
package com.gsp.weichat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Value("${sys.version}")
	private String systemPublish;
	
	@Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户信息相关API")
                .pathMapping("/")
                .select()
                .paths(new Predicate<String>() {
					@Override
					public boolean apply(String input) {
						return input.matches("/v1/exam_face/user/.*");
					}
				})//过滤的接口
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("用户信息相关API")
                        .version(systemPublish)
                        .build());
    }
	
	@Bean
    public Docket examApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("答题相关API")
                .pathMapping("/")
                .select()
                .paths(new Predicate<String>() {
					@Override
					public boolean apply(String input) {
						return input.matches("/v1/exam_face/exam/.*");
					}
				})//过滤的接口
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("答题相关API")
                        .version(systemPublish)
                        .build());
    }
}
