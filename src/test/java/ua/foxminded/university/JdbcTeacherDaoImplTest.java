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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import ua.foxminded.university.dao.department.JdbcDepartmentContentDaoImpl;
import ua.foxminded.university.dao.teacher.JdbcTeacherDoaImpl;
import ua.foxminded.university.domain.Teacher;

public class JdbcTeacherDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcTeacherDoaImpl jdbcTeacherDoaImpl;
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
        jdbcTeacherDoaImpl = new JdbcTeacherDoaImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("createTest should add new Teacher")
    void createTest_ShouldAddNewTeacher() {
        Teacher teacher = Teacher.builder()
                .withCardId(7)
                .withFirstName("first_name")
                .withLastName("last_name")
                .withContact("contact")
                .withDepartmentId(1)
                .withPosition("position")
                .build();
        Optional<Teacher> expected = Optional.ofNullable(teacher);
        jdbcTeacherDoaImpl.create("first_name", "last_name", "contact", 1, "position");
        Optional<Teacher> actual = jdbcTeacherDoaImpl.findTeacherById(7);
        assertThat(actual, equalTo(expected));
     }

    @Test
    @DisplayName("getAllTest should Return subList of Teachers")
    void getAllTest_ShouldReturnSublistOfTeacherList() {
        Teacher teacher1 = Teacher.builder()
                .withCardId(1)
                .withFirstName("Hadley")
                .withLastName("Sheppard")
                .withContact("04411111")
                .withDepartmentId(1)
                .withPosition("LECTURER")
                .build();
        Teacher teacher2 = Teacher.builder()
                .withCardId(2)
                .withFirstName("Ricky")
                .withLastName("Kirby")
                .withContact("04422222")
                .withDepartmentId(2)
                .withPosition("SENIOR_LECTURER")
                .build();
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher1);
        expected.add(teacher2);
        List<Teacher> actual = jdbcTeacherDoaImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("findById return Teacher")
    void findByIdTest_returnTeacher() {
        Teacher teacher1 = Teacher.builder()
                .withCardId(1)
                .withFirstName("Hadley")
                .withLastName("Sheppard")
                .withContact("04411111")
                .withDepartmentId(1)
                .withPosition("LECTURER")
                .build();
        Optional<Teacher> expected = Optional.ofNullable(teacher1);
        Optional<Teacher> actual = jdbcTeacherDoaImpl.findTeacherById(1);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("deleteTest should delete Teacher")
    void deleteTest_ShouldDeleteTeacher() {
        jdbcTeacherDoaImpl.create("first_name", "last_name", "contact", 1, "position");
        jdbcTeacherDoaImpl.delete(7);
        Optional<Teacher> actual = jdbcTeacherDoaImpl.findTeacherById(7);
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("updateTest should Update Teacher")
    void updateTest_ShouldUpdateTeacher() {
        Teacher teacher = Teacher.builder()
                .withCardId(5)
                .withFirstName("first_name")
                .withLastName("last_name")
                .withContact("contact")
                .withDepartmentId(1)
                .withPosition("position")
                .build();
        Optional<Teacher> expected = Optional.ofNullable(teacher);
        jdbcTeacherDoaImpl.update(5, "first_name", "last_name", "contact", 1, "position");
        Optional<Teacher> actual = jdbcTeacherDoaImpl.findTeacherById(5);
        assertThat(actual, equalTo(expected));
    }
}
