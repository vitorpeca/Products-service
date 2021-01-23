package com.br.products.configurations;

import com.microsoft.applicationinsights.core.dependencies.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "database.zone")
public class DatabaseCollectionProperties {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseCollectionProperties.class);

	@Value("#{environment.DATABASE_ZONE_COLLECTIONS}")
	private String propertiesFromEnv;

	private Map<String, String> collections;

	public String getCollectionByMap(final String collection) {

		return collections.get(collection);
	}

	public Map<String, String> getCollections() {

		return collections;
	}

	public void setCollections(final Map<String, String> collections) {

		if (StringUtils.isNotBlank(propertiesFromEnv)) {

			// HACK: To fix spring relaxed binding behavior when inject maps
			try {
				this.collections = new Gson().fromJson(propertiesFromEnv, Map.class);
			} catch (final Exception e) {
				logger.error(e.getMessage(), e);
				this.collections = collections;
			}
		} else {
			this.collections = collections;
		}
	}
}