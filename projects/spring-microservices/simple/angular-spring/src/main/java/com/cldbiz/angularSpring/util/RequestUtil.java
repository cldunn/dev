package com.cldbiz.angularSpring.util;

import java.util.UUID;

public abstract class RequestUtil {
	public static String generateCsrfToken() {
		return UUID.randomUUID().toString().replace(":", "").replace("-", "");
	}
}
