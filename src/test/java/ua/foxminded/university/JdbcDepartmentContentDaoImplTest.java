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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ua.foxminded.university.configuration.ApplicationConfig;
import ua.foxminded.university.dao.department.DepartmentContentDao;
import ua.foxminded.university.dao.department.JdbcDepartmentContentDaoImpl;
import ua.foxminded.university.domain.department.DepartmentContent;

public class JdbcDepartmentContentDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcDepartmentContentDaoImpl jdbcDepartmentContentDaoImpl;
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
        jdbcDepartmentContentDaoImpl = new JdbcDepartmentContentDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("deleteTest should delete DepartmentContent")
    void deleteTest_ShouldDeleteDepartmentContent() {
        jdbcDepartmentContentDaoImpl.delete(1);
        Optional<DepartmentContent> actual = jdbcDepartmentContentDaoImpl.findDepartmentContentById(1);
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("createTest should add new DepartmentContent")
    void createTest_ShouldAddNewDepartmentContent() {
        DepartmentContent department = DepartmentContent.builder()
                .withDepartmentContentId(13)
                .withDepartmentId(1)
                .withStudentId(1)
                .withGroupId(1)
                .withTeacherId(1)
                .withGroupId(1)
                .withTimetableId(1)
                .build();
        Optional<DepartmentContent> expected = Optional.ofNullable(department);
        jdbcDepartmentContentDaoImpl.create(1, 1, 1, 1, 1);
        Optional<DepartmentContent> actual = jdbcDepartmentContentDaoImpl.findDepartmentContentById(13);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("findById return DepartmentContent")
    void findByIdTest_returnDepartmentContent() {
        DepartmentContent departmentContent = DepartmentContent.builder()
                .withDepartmentContentId(2)
                .withDepartmentId(2)
                .withStudentId(2)
                .withGroupId(1)
                .withTeacherId(3)
                .withTimetableId(2)
                .build();
        Optional<DepartmentContent> expected = Optional.ofNullable(departmentContent);
        Optional<DepartmentContent> actual = jdbcDepartmentContentDaoImpl.findDepartmentContentById(2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getAllTest should Return subList of DepartmentContent")
    void getAllTest_ShouldReturnSublistOfDepartmentContentList() {
        DepartmentContent departmentCont1 = DepartmentContent.builder()
                .withDepartmentContentId(2)
                .withDepartmentId(2)
                .withStudentId(2)
                .withGroupId(1)
                .withTeacherId(3)
                .withTimetableId(2)
                .build();
        DepartmentContent departmentCont2 = DepartmentContent.builder()
                .withDepartmentContentId(3)
                .withDepartmentId(3)
                .withStudentId(3)
                .withGroupId(2)
                .withTeacherId(5)
                .withTimetableId(3)
                .build();
        List<DepartmentContent> expected = new ArrayList<>();
        expected.add(departmentCont1);
        expected.add(departmentCont2);
        List<DepartmentContent> actual = jdbcDepartmentContentDaoImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("updateTest should Update DepartmentContent")
    void updateTest_ShouldUpdateDepartmentContent() {
        DepartmentContent departmentContent = DepartmentContent.builder()
                .withDepartmentContentId(3)
                .withDepartmentId(3)
                .withStudentId(3)
                .withGroupId(3)
                .withTeacherId(3)
                .withTimetableId(3)
                .build();
        Optional<DepartmentContent> expected = Optional.ofNullable(departmentContent);
        jdbcDepartmentContentDaoImpl.update(3, 3, 3, 3, 3, 3);
        Optional<DepartmentContent> actual = jdbcDepartmentContentDaoImpl
                .findDepartmentContentById(3);
        assertThat(actual, equalTo(expected));
    }
}
