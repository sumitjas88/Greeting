package hello.repository;

import hello.model.Greeting;
import hello.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class GreetingRepository {



   public JdbcTemplate jdbcTemplate;
    @Autowired
    public GreetingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        GreetingRepositoryInitialize();
    }

    public void GreetingRepositoryInitialize() {
        DataSource dataSource = new DataSource();
        dataSource.createDatabas();
    }

    public int insert(Greeting greeting, Person person) {
        System.out.println("In Repository");
//        Connection connection = jdbcTemplate.batchUpdate("sql",new BatchPreparedStatementSetter(){
//
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//
//            }
//
//            @Override
//            public int getBatchSize() {
//                return 0;
//            }
//        });

        return jdbcTemplate.update(
                "INSERT INTO greeting(name) values(?)",
                greeting.getContent());


    }
    public java.util.List<Greeting> findGreeting(String name) {
        System.out.println("**************finding greeting ****************");

        return jdbcTemplate.query(
                "select id,name from greeting where name =?",new Object[]{name},
                new RowMapper<Greeting>() {
                    @Override
                    public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Greeting greeting = new Greeting();
                        greeting.setId(rs.findColumn("id"));
                        greeting.setContent(rs.getString("name"));
                        return greeting;
                    }
                });

    }
}
