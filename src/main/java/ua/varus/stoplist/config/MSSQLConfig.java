package ua.varus.stoplist.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class MSSQLConfig {

    @Bean(name = "dm-msql")
    public DriverManagerDataSource getDriverManagerDatasourceMSSQL() {
        System.out.println("-------- MISCROSOFT JDBC Connection Testing RISKI-DB------------");

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MISCROSOFT JDBC Driver? Include in your library path!");
            e.printStackTrace();


        }

        String dbUrl = "jdbc:sqlserver://172.20.1.94;databaseName=InfoDB";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setUrl(dbUrl);
            dataSource.setUsername("sdobrohotov");
            dataSource.setPassword("1Few)8#22CwZ");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return dataSource;
    }

    @Bean(name = "riski-mssql")
    public JdbcTemplate jdbcTemplateMSSQL(@Qualifier("dm-msql") DriverManagerDataSource getDriverManagerDatasourceMSSQL){
        return new JdbcTemplate(getDriverManagerDatasourceMSSQL);
    }
}
