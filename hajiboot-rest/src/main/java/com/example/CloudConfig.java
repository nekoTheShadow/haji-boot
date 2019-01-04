package com.example;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig {
	@Bean
	DataSource datasouce() {
		var poolConfig = new PooledServiceConnectorConfig.PoolConfig(5, 30, 300);
		var config = new DataSourceConfig(poolConfig, null);
		return connectionFactory().dataSource(config);
	}
}
