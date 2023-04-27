package com.nanemo.repository;

import com.nanemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPerson() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addPeopleWithBatchMethod() {
        List<Person> people = getPeople();

        jdbcTemplate.batchUpdate("INSERT INTO Person (name, email, age, address) VALUES (?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, people.get(i).getName());
                        ps.setString(2, people.get(i).getEmail());
                        ps.setInt(3, people.get(i).getAge());
                        ps.setString(4, people.get(i).getAddress());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });
    }

    public void addPeopleWithSimpleUpdateMethod() {
        List<Person> people = getPeople();

        people.forEach(person -> jdbcTemplate.update("INSERT INTO Person (name, email, age, address) VALUES (?,?,?,?)",
                person.getName(), person.getEmail(), person.getAge(), person.getAddress()));
    }

    public void dropPersonTable() {
        jdbcTemplate.update("DROP TABLE IF EXISTS Person; " + "CREATE TABLE Person(\n" +
                "    person_id Integer AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name      VARCHAR(60),\n" +
                "    email     VARCHAR(60),\n" +
                "    age       SMALLINT,\n" +
                "    address   VARCHAR(100)\n" +
                ")");
    }

    private List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person("name" + i, "test@email.com", (byte) 30, "Country, City, 00000"));
        }

        return people;
    }


}
