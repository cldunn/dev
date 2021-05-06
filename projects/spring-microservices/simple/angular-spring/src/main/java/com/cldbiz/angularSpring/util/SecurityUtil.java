package com.cldbiz.angularSpring.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cldbiz.angularSpring.util.AntiSamyUtil;
import com.cldbiz.angularSpring.common.JsonFailure;
import com.cldbiz.angularSpring.common.JsonMessage;
import com.cldbiz.angularSpring.spring.bean.AfwMessageSource;

import com.google.json.JsonSanitizer;


public class SecurityUtil {
	private static final Logger logger = LoggerFactory.getLogger(AntiSamyUtil.class);
	
	@Autowired
	private static AfwMessageSource messagSource;

	public static String sanitize(String input) {
		if (StringUtils.isEmpty(input)) {
			return  input;
		}
		
		try {
			// Back out any Double Encoding
			String decodedInput = URLDecoder.decode(input, "UTF-8");
			List<String> inputs = new ArrayList<String>(Arrays.asList(input));
			while (!decodedInput.equals(inputs.get(inputs.size() - 1))) {
				inputs.add(decodedInput);
				decodedInput = URLDecoder.decode(decodedInput, "UTF-8");
			}
			
			if (inputs.size() > 1) {
				input = inputs.get(inputs.size() - 2);
			}
		} catch (UnsupportedEncodingException e1) {
			throw new JsonFailure(new JsonMessage(messagSource.getMessage("security.antisamy.errors")));
		}
		
		String output;
		if (isJsonObject(input)) {
			output = JsonSanitizer.sanitize(input);
			output = AntiSamyUtil.clean(output);
			if (!isJsonObject(output)) {
				throw new JsonFailure(new JsonMessage(messagSource.getMessage("security.antisamy.errors")));
			}
		}
		else {
			output = AntiSamyUtil.clean(input);
		}
		
		return output;
	}
	
	private static boolean isJsonObject(String input) {
		input = input.trim();
		return (input.startsWith("{") && input.endsWith("}")) || 
			   (input.startsWith("[") && input.endsWith("]")) ||
			   (input.startsWith("[{") && input.endsWith("}]"));
	}
	
	
}
