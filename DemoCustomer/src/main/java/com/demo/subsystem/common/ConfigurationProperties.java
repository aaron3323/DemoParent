package com.demo.subsystem.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.ReloadingStrategy;

public class ConfigurationProperties {
	private static Map<String, PropertiesConfiguration> map = new HashMap<String, PropertiesConfiguration>();
	
	public static void load(String key, String file) throws ConfigurationException {
		load(key, file, false, null);
	}
	
	public static void load(String key, String file, boolean reload, ReloadingStrategy reloadingStrategy) throws ConfigurationException {
		PropertiesConfiguration config = new PropertiesConfiguration(file);
		if(reload) {
			config.setReloadingStrategy(reloadingStrategy);
		}
	}
	
	public static PropertiesConfiguration get(String key) {
		PropertiesConfiguration config = map.get(key);
		return config;
	}
}
