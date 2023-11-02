package com.project.library.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Profile("!test")
@EnableJpaRepositories(basePackages = { "com.project.library.repository" })
public class PostgresConfiguration {
    @Bean
    public DataSource dataSource() throws IOException {
        DataSource postgresDataBase = DataSourceBuilder
                .create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("postgres")
                .build();
        return postgresDataBase;
    }
}