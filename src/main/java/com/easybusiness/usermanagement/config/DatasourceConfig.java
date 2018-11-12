package com.easybusiness.usermanagement.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.easybusiness.usermanagement.repository")*/
public class DatasourceConfig {

	/*@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase edb = db.setType(EmbeddedDatabaseType.)
		
	}*/
	
}
