package server.config;

import org.hsqldb.jdbcDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import server.ServerMain;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@ComponentScan(basePackageClasses = ServerMain.class)
@EnableTransactionManagement
public class ServerConfigration {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(embeddedDatabase());
    }

    /**
     * 내장 데이터베이스의 DataSource
     */
    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
        factory.setDatabaseConfigurer(databaseConfigurer());
        factory.setDatabasePopulator(populator());
        return factory.getDatabase();
    }

    @Bean
    public EmbeddedDatabaseConfigurer databaseConfigurer() {
        return new EmbeddedDatabaseConfigurer() {
            @Override
            public void configureConnectionProperties(ConnectionProperties properties, String databaseName) {
                properties.setUrl("jdbc:hsqldb:mem:testdb;sql.syntax_ora=true");
                properties.setUsername("sa");
                properties.setPassword("");
                properties.setDriverClass(jdbcDriver.class);
            }

            @Override
            public void shutdown(DataSource dataSource, String databaseName) {
                //AbstractEmbeddedDatabaseConfigurer의 shutdown메소드 로직
                try {
                    Connection connection = dataSource.getConnection();
                    Statement stmt = connection.createStatement();
                    stmt.execute("SHUTDOWN");
                } catch (SQLException ex) {
                }
            }
        };
    }

    @Bean
    public DatabasePopulator populator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new DefaultResourceLoader().getResource("sql.sql"));
        return populator;
    }

}
