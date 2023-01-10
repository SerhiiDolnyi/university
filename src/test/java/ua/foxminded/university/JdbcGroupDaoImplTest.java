package ua.foxminded.university;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import ua.foxminded.university.dao.group.JdbcGroupDaoImpl;
import ua.foxminded.university.domain.Group;

public class JdbcGroupDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcGroupDaoImpl jdbcGroupDaoImpl;
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
        jdbcGroupDaoImpl = new JdbcGroupDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("createTest should add new Group")
    public void createTest_ShouldAddNewGroup() {
        Group group = new Group (6, "3BB");
        Optional<Group> expected = Optional.ofNullable(group);
        jdbcGroupDaoImpl.create("3BB");
        Optional<Group> actual = jdbcGroupDaoImpl.findGroupById(6);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getAllTest should Return subList of Groups")
    public void getAllTest_ShouldReturnSublistOfGroupList() {
        List<Group> expected = new ArrayList<>();
        expected.add(new Group(1,"1AA"));
        expected.add(new Group(2,"1BB"));
        List<Group> actual = jdbcGroupDaoImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("updateTest should Update Group")
    void updateTest_ShouldUpdateGroup() {
        Group updatedGroup = new Group(2, "1SS");
        Optional<Group> expected = Optional.ofNullable(updatedGroup);
        jdbcGroupDaoImpl.update(2, "1SS");
        Optional<Group> actual = jdbcGroupDaoImpl.findGroupById(2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("findById return group")
    public void findByIdTest_returnGroup() {
        Group expectedGroup = new Group(1, "1AA");
        Optional<Group> expected = Optional.ofNullable(expectedGroup);
        Optional<Group> actual = jdbcGroupDaoImpl.findGroupById(1);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("deleteTest should delete Group")
    void deleteTest_ShouldDeleteGroup() {
        jdbcGroupDaoImpl.delete(1);
        Optional<Group> actual = jdbcGroupDaoImpl.findGroupById(1);
        assertFalse(actual.isPresent());
    }
}
