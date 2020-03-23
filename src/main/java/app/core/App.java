package app.core;

import app.core.entity.Person;
import app.core.service.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger logger=LoggerFactory.getLogger(App.class);

    private final PersonRepository personRepository;

    private final JdbcTemplate jdbcTemplate;

    public App(PersonRepository personRepository, JdbcTemplate jdbcTemplate) {
        this.personRepository = personRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("delete from jpa.person");
        personRepository.insert(new Person(1L, "Anna", "Barkova", "Moscow"));
        personRepository.insert(new Person(2L, "Mike", "Katkov", "London"));
        personRepository.insert(new Person(3L, "Sasha", "Chest", "Smolensk"));
        personRepository.findAll().forEach(omega->logger.info(String.valueOf(omega)));
    }


}
