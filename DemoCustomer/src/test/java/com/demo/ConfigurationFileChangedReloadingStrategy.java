package com.demo;

import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class ConfigurationFileChangedReloadingStrategy extends FileChangedReloadingStrategy {

	@Override
	public void setConfiguration(FileConfiguration configuration) {
		System.out.println("setConfiguration");
		super.setConfiguration(configuration);
	}

	@Override
	public boolean reloadingRequired() {
		System.out.println("reloadingRequired");
		return super.reloadingRequired();
	}

	@Override
	public void reloadingPerformed() {
		System.out.println("reloadingPerformed");
		super.reloadingPerformed();
	}

	@Override
	protected void updateLastModified() {
		System.out.println("updateLastModified");
		super.updateLastModified();
	}

}
