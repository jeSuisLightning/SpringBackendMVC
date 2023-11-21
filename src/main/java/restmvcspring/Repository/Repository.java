package restmvcspring.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import restmvcspring.Models.Contacts;

import javax.sql.DataSource;

@org.springframework.stereotype.Repository
@Component
@Configuration
public class Repository implements WebMvcConfigurer {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public BeanPropertyRowMapper beanPropertyRowMapper(){
        return new BeanPropertyRowMapper<>(Contacts.class);

    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("demo2228");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        return dataSource;
    }

}
