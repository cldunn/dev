package com.cldbiz.boot.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.util.JavaScriptUtils;

import com.cldbiz.boot.config.BootExecutionContext;

public class BootMessageSource extends ReloadableResourceBundleMessageSource {
	private static ConcurrentHashMap<String, MessageSourceAccessor> cacheMessageAccessors = new ConcurrentHashMap<String, MessageSourceAccessor>();
	private List<String> baseNames = new ArrayList<String>();
	
	private MessageSourceAccessor getMessageSourceAccessor() {
		Locale locale = BootExecutionContext.getUserProfileDto().getLocale();
		
		MessageSourceAccessor msgSrcAccessor = cacheMessageAccessors.get(locale.getLanguage());
		if (msgSrcAccessor == null) {
			msgSrcAccessor = new MessageSourceAccessor(this, locale);
			cacheMessageAccessors.put(locale.getLanguage(), msgSrcAccessor);
		}
		
		return msgSrcAccessor;
	}
	
	public void setBasename(String basename) {
		this.setBasenames(basename);
	}
	
	public List<String> getBaseNames() {
		return this.baseNames;
	}

	public void setBasenames(String... basenames) {
		this.baseNames.clear();
		for (int i = 0; i < basenames.length; i++) {
			this.baseNames.add(basenames[i]);
		}
		
		BootMessageSource parentMessageSource = (BootMessageSource) this.getParentMessageSource();
		if (parentMessageSource != null) {
			this.baseNames.addAll(parentMessageSource.getBaseNames());
		}

		super.setBasenames(this.baseNames.toArray(new String[this.baseNames.size()]));
	}

	public String getMessage(String key) {
		MessageSourceAccessor msgSrc = getMessageSourceAccessor();
		String msg = msgSrc.getMessage(key);
		
		return JavaScriptUtils.javaScriptEscape(msg);
	}

	public String getMessage(String key, Object... args) {
		MessageSourceAccessor msgSrc = getMessageSourceAccessor();
		String msg = msgSrc.getMessage(key, args);
		
		return JavaScriptUtils.javaScriptEscape(msg);
	}

	public Map<String, String> getAllKeyPrefixedMessages(String... keyPrefixes) {
		List<String> prefixKeys = Arrays.asList(keyPrefixes);
		Map<String, String> allKeyPrefixes = new HashMap<String, String>();
		
		List<BootMessageSource> hierarchy = new ArrayList<BootMessageSource>();
		hierarchy.add(this);
		
		Locale locale = BootExecutionContext.getUserProfileDto().getLocale();
		PropertiesHolder propertiesHolder = getMergedProperties(locale);
		Properties properties = propertiesHolder.getProperties();
		Enumeration<Object> keys = properties.keys();
		
		while(keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			for (String prefix: prefixKeys) {
				if (key.startsWith(prefix)) {
					String msg = properties.getProperty(key);
					allKeyPrefixes.put(key, JavaScriptUtils.javaScriptEscape(msg));
				}
			}
		}
		
		return allKeyPrefixes;
	}
}
