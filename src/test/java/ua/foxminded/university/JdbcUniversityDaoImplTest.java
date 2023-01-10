package ua.foxminded.university;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ua.foxminded.university.dao.department.JdbcDepartmentContentDaoImpl;
import ua.foxminded.university.dao.university.JdbcUniversityDaoImpl;
import ua.foxminded.university.domain.university.University;

public class JdbcUniversityDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcUniversityDaoImpl jdbcUniversityDaoImpl;
    private static EmbeddedDatabaseBuilder  embeddedDatabase = new EmbeddedDatabaseBuilder();

    @BeforeAll
    public static void initializeTestDatabase() {
        embeddedDatabase.setType(EmbeddedDatabaseType.H2);
    }

    @BeforeEach
    public void resetDatabase() {
        DataSource dataSource = (DataSource) embeddedDatabase
          .addScript("classpath:schema.sql")
          .addScript("classpath:data.sql")
          .build();
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcUniversityDaoImpl = new JdbcUniversityDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("deleteTest should delete University")
    void deleteTest_ShouldDeleteUniversity() {
        jdbcUniversityDaoImpl.delete(1);
        Optional<University> actual = jdbcUniversityDaoImpl.findDepartmentById(1);
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("createTest should add new University")
    void createTest_ShouldAddNewUniversity() {
        University university = new University(1, "IT_DEPARTMENT");
        Optional<University> expected = Optional.ofNullable(university);
        jdbcUniversityDaoImpl.create("IT_DEPARTMENT");
        Optional<University> actual = jdbcUniversityDaoImpl.findDepartmentById(1);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("updateTest should Update University")
    void updateTest_ShouldUpdateUniversity() {
        University university = new University(1, "TEST_DEPARTMENT");
        Optional<University> expected = Optional.ofNullable(university);
        jdbcUniversityDaoImpl.update(1, "TEST_DEPARTMENT");
        Optional<University> actual = jdbcUniversityDaoImpl.findDepartmentById(1);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("findById return University")
    void findByIdTest_returnUniversity() {
        University university = new University(1, "IT_DEPARTMENT");
        Optional<University> expected = Optional.ofNullable(university);
        jdbcUniversityDaoImpl.create("IT_DEPARTMENT");
        Optional<University> actual = jdbcUniversityDaoImpl.findDepartmentById(1);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getAllTest should Return subList of University")
    void getAllTest_ShouldReturnSublistOfUniversity() {
        List<University> expected = new ArrayList<>();
        expected.add(new University(1, "IT_DEPARTMENT"));
        expected.add(new University(2, "MATH_DEPARTMENT"));
        List<University> actual = jdbcUniversityDaoImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }
}
