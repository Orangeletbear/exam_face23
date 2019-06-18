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
package com.gsp.weichat.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

/**
 * @author shaopeng.gong
 * @since JDK 8
 * @version v1.0
 */

@Component
public class HttpUtil {
	
	/**
	 * 发送get请求，并返回结果
	 * @param uri
	 * @return
	 * @author GongShaopeng
	 */
	public Object getRequest(String uri) {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true); // 设置该连接是可以输出的
			connection.setRequestMethod("GET"); // 设置请求方式
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) { // 读取数据
				result.append(line + "\n");
			}
			connection.disconnect();

		} catch (Exception e) {
			throw new RuntimeException("get请求发送失败");
		}
		
		return result.toString();
	}
	
	/**
	 * 发送post请求并返回结果
	 * @param uri
	 * @param param form表单内容
	 * @return
	 * @author GongShaopeng
	 */
	public Object postRequest(String uri, String param) {
		StringBuilder result = new StringBuilder();
		try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(param);
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException("post请求发送失败");
        }
		return result.toString();
	}
	
	public Object jsonRequest(String uri, String json) {
		StringBuilder result = new StringBuilder();
		try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(json);
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();
        } catch (Exception e) {
        	throw new RuntimeException("json请求发送失败");
        }
		
		return result.toString();
	}
}
