package hello.repository;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataSource {

    SimpleDriverDataSource dataSource;

    public void createDatabas(){
        dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");
    }
}
