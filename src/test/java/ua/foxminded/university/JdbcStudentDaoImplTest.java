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
import ua.foxminded.university.dao.student.JdbcStudentDaoImpl;
import ua.foxminded.university.domain.Student;

public class JdbcStudentDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcStudentDaoImpl jdbcStudentDaoImpl;
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
        jdbcStudentDaoImpl = new JdbcStudentDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("findByIdTest return student")
    void findByIdTest_returnStudent() {
        Student student = Student.builder()
                .withStudentId(3)
                .withFirstName("Karley")
                .withLastName("Buchanan")
                .withContact("04433333")
                .withGroupId(2)
                .build();
        Optional<Student> expected = Optional.ofNullable(student);
        Optional<Student> actual = jdbcStudentDaoImpl.findStudentById(3);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("createTest should add new Student")
    void createTest_ShouldAddNewStudent() {
        Student student = Student.builder()
             .withStudentId(11)
             .withFirstName("first_name")
             .withLastName("last_name")
             .withContact("contact")
             .withGroupId(1)
             .build();
    Optional<Student> expected = Optional.ofNullable(student);
    jdbcStudentDaoImpl.create("first_name", "last_name", "contact", 1);
    Optional<Student> actual = jdbcStudentDaoImpl.findStudentById(11);
    assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("updateTest should Update Student")
    void updateTest_ShouldUpdateStudent() {
        Student student = Student.builder()
                .withStudentId(2)
                .withFirstName("first_name")
                .withLastName("last_name")
                .withContact("contact")
                .withGroupId(1)
                .build();
        Optional<Student> expected = Optional.ofNullable(student);
        jdbcStudentDaoImpl.update(2, "first_name", "last_name", "contact", 1);
        Optional<Student> actual = jdbcStudentDaoImpl.findStudentById(2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getAllTest should Return subList of Student")
    void getAllTest_ShouldReturnSublistOfStudentList() {
        Student student1 = Student.builder()
                 .withStudentId(1)
                 .withFirstName("Naima")
                 .withLastName("Sheppard")
                 .withContact("04411111")
                 .withGroupId(1)
                 .build();
        Student student2 = Student.builder()
                 .withStudentId(2)
                 .withFirstName("Kingston")
                 .withLastName("Blackwell")
                 .withContact("04422222")
                 .withGroupId(1)
                 .build();
        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);
        List<Student> actual = jdbcStudentDaoImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("deleteTest should delete student")
    void deleteTest_ShouldDeleteGroup() {
        jdbcStudentDaoImpl.delete(1);
        Optional<Student> actual = jdbcStudentDaoImpl.findStudentById(1);
        assertFalse(actual.isPresent());
    }
}
