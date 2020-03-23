package app.core.service;

import app.core.entity.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /** select all records from table with suitable ids an cast them to Person entity with power Of BeanPropertyRowMapper
     * @return list of persons
     */
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM jpa.person", new BeanPropertyRowMapper<>(Person.class));
    }

    /** add record to the Person table
     * @param person
     * @return number of added rows
     */
    public int insert(Person person) {
        return jdbcTemplate.update("INSERT  INTO jpa.person (id,first_name,last_name,address) values (?,?,?,?)",
                person.getId(), person.getFirstName(), person.getLastName(), person.getAddress());
    }

    /** delete record from the Person table
     * @param person
     * @return number of deleted rows
     */
    public int delete(Person person) {
        String query = ("delete from jpa.person where id=" + person.getId());
        return jdbcTemplate.update(query);
    }

    /** update record by id in the Person table
     * @param person
     * @return number of added rows
     */
    public int update(Person person) {
        String query = ("update jpa.person set first_name=" + person.getFirstName() + ",last_name=" + person.getLastName() + " where id=" + person.getId());
        return jdbcTemplate.update(query);
    }


}
