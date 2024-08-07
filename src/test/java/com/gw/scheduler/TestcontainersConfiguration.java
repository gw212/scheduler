package com.gw.scheduler;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>("postgres:latest")
				.withDatabaseName("test")
				.withUsername("app_user")
				.withPassword("test-!132")
				.withCopyFileToContainer(MountableFile.forClasspathResource("test_schema.sql"),
						"/docker-entrypoint-initdb.d/your_local_file.sql");
	}

}
