package ua.foxminded.university;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Date;
import java.sql.Time;
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
import ua.foxminded.university.dao.timetable.JdbcTimetableDaoImpl;
import ua.foxminded.university.domain.timetable.Timetable;

public class JdbcTimetableDaoImplTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcTimetableDaoImpl jdbcTimetableDaoImpl;
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
        jdbcTimetableDaoImpl = new JdbcTimetableDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("deleteTest should delete Timetable")
    void deleteTest_ShouldDeleteTimetable() {
        jdbcTimetableDaoImpl.delete(1);
        Optional<Timetable> actual = jdbcTimetableDaoImpl.findTimetableById(1);
        assertFalse(actual.isPresent());
    }

    @Test
    @DisplayName("createTest should add new Timetable")
    void createTest_ShouldAddNewTeacher() {
      Date date = Date.valueOf("2000-01-04");
      Time startLecture = Time.valueOf("11:00:00");
      Time endLecture = Time.valueOf("12:00:00");
        Timetable timetable = Timetable.builder()
                .withTimetableId(13)
                .withDate(date)
                .withStartLecture(startLecture)
                .withEndLecture(endLecture)
                .withLocation("location")
                .withGroupId(1)
                .withSubject("subject")
                .withTeacherId(1)
                .build();
        Optional<Timetable> expected = Optional.ofNullable(timetable);
        jdbcTimetableDaoImpl.create(date, startLecture, endLecture, "location", 1, "subject", 1);
        Optional<Timetable> actual = jdbcTimetableDaoImpl.findTimetableById(13);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getAllTest should Return subList of Timetables")
    void getAllTest_ShouldReturnSublistOfTimetableList() {
        Timetable timetable1 = Timetable.builder()
                .withTimetableId(2)
                .withDate(Date.valueOf("2022-01-01"))
                .withStartLecture(Time.valueOf("10:00:00"))
                .withEndLecture(Time.valueOf("1:00:00"))
                .withLocation("Hall 1")
                .withGroupId(1)
                .withSubject("MATH")
                .withTeacherId(3)
                .build();
        Timetable timetable2 = Timetable.builder()
                .withTimetableId(3)
                .withDate(Date.valueOf("2000-01-01"))
                .withStartLecture(Time.valueOf("09:00:00"))
                .withEndLecture(Time.valueOf("10:00:00"))
                .withLocation("Hall 2")
                .withGroupId(2)
                .withSubject("DESIGN")
                .withTeacherId(5)
                .build();
        List<Timetable> expected = new ArrayList<>();
        expected.add(timetable1);
        expected.add(timetable2);
        List<Timetable> actual = jdbcTimetableDaoImpl.getAll().subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("findById return Timetable")
    void findByIdTest_returnTimetable() {
        Date date = Date.valueOf("2000-01-04");
        Time startLecture = Time.valueOf("11:00:00");
        Time endLecture = Time.valueOf("12:00:00");
        Timetable timetable1 = Timetable.builder()
                .withTimetableId(13)
                .withDate(date)
                .withStartLecture(startLecture)
                .withEndLecture(endLecture)
                .withLocation("location")
                .withGroupId(1)
                .withSubject("subject")
                .withTeacherId(1)
                .build();
        Optional<Timetable> expected = Optional.ofNullable(timetable1);
        Optional<Timetable> actual = jdbcTimetableDaoImpl.findTimetableById(13);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("updateTest should Update Timetable")
    void updateTest_ShouldUpdateTimetable() {
        Timetable timetable = Timetable.builder()
                .withTimetableId(10)
                .withDate(Date.valueOf("2000-01-01"))
                .withStartLecture(Time.valueOf("09:00:00"))
                .withEndLecture(Time.valueOf("10:00:00"))
                .withLocation("location")
                .withGroupId(1)
                .withSubject("subject")
                .withTeacherId(1)
                .build();
        Optional<Timetable> expected = Optional.ofNullable(timetable);
        jdbcTimetableDaoImpl.update(10, Date.valueOf("2000-01-01"),
                Time.valueOf("09:00:00"), Time.valueOf("10:00:00"),
                "location", 1, "subject", 1);
        Optional<Timetable> actual = jdbcTimetableDaoImpl.findTimetableById(10);
        assertThat(actual, equalTo(expected));
    }
}
