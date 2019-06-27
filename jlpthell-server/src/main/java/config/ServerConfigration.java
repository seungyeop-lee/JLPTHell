package config;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import server.manager.ConnectionManager;

@Configuration
@ComponentScan(basePackageClasses = ConnectionManager.class)
public class ServerConfigration {
	
	/**
	 * 내장 데이터베이스의 DataSource
	 */
	@Bean
	public EmbeddedDatabase embeddedDatabase() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseConfigurer(databaseConfigurer());
		factory.setDatabaseType(EmbeddedDatabaseType.HSQL);
		factory.setDatabasePopulator(populator());
		return factory.getDatabase();
	}
	
	@Bean
	public EmbeddedDatabaseConfigurer databaseConfigurer() {
		return new EmbeddedDatabaseConfigurer() {
			@Override
			public void configureConnectionProperties(ConnectionProperties properties, String databaseName) {
				properties.setUrl("jdbc:hsqldb:hsql://localhost/testdb;sql.syntax_ora=true");
				properties.setUsername("sa");
				properties.setPassword("");
				properties.setDriverClass(JDBCDriver.class);
			}
			@Override
			public void shutdown(DataSource dataSource, String databaseName) {}
		};
	}
	
	@Bean
	public DatabasePopulator populator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new DefaultResourceLoader().getResource("sql.sql"));
		return populator;
	}
	
}
