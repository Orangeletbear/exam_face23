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
package com.gsp.weichat.common.util.face_util;

import com.baidu.aip.face.AipFace;

/**
 * AipFace单例工厂
 * @author shaopeng.gong
 * @since JDK 8
 * @version v3.0
 */
public class AipFaceFactory {
	public static final String APP_ID = "16220026";
    public static final String API_KEY = "gI6aBkOmfzXMsD1e4hLmCGns";
    public static final String SECRET_KEY = "A7tW7b6LmeTICa3zK5aOTOgh518SygQ8";
	private static AipFace aipFace = null;
	
	private AipFaceFactory () {}
	
	public static AipFace getAipFace() {
		if(null == aipFace) {
			aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);
			aipFace.setConnectionTimeoutInMillis(6000);
		}
		return aipFace;
	}
}
