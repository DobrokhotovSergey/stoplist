package ua.varus.stoplist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConfig {

    @Primary
    @Bean(name = "dm-postgresql")
    public DriverManagerDataSource getDriverManagerDatasource() {
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();


        }

        String dbUrl = "jdbc:postgresql://db.rm.net.omega:5432/stoplist";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setUrl(dbUrl);
            dataSource.setUsername("serg981");
            dataSource.setPassword("amatory89");
        }catch (Exception ex){
            System.out.println(ex);
        }
        return dataSource;
    }

    @Bean(name="riski-postgresql")
    public JdbcTemplate jdbcTemplateRiski(@Qualifier("dm-postgresql") DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }



}
