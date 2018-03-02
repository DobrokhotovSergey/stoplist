package ua.varus.stoplist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConfig {

    @Bean
    public DriverManagerDataSource getDriverManagerDatasource() {
        String dbUrl = "jdbc:postgresql://ec2-23-21-96-70.compute-1.amazonaws.com:5432/d9s122lkd6dc5c?sslmode=require";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setUrl(dbUrl);
            dataSource.setUsername("yefzqnfdvaghvd");
            dataSource.setPassword("3fc956464fdc3567b06c26160334e92fd16f90df302849bd596548717d4dd865");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
