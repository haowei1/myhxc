package com.hdy.myhxc.util;

import java.util.UUID;

/**
 * 生成DB数据主键用的util类
 *
 * @author m760384371
 */
public class UUIDUtil {
	
	/**
	 * 生成uuid
	 * @return uuid
	 */
	public final static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
