package com.cldbiz.afw.util;

import java.util.UUID;

public abstract class RequestUtil {
	public static String generateCsrfToken() {
		return UUID.randomUUID().toString().replace(":", "").replace("-", "");
	}
}
