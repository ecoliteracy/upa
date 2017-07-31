package com.upa.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationProperties {
	@Autowired
	private Environment env;

	public String getProperty(String propName) {
		return env.getProperty(propName);
	}
}
