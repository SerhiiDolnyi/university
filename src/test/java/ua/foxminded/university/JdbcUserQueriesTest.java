package ua.foxminded.university;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

import ua.foxminded.university.dao.userqueries.JdbcUserQueries;
import ua.foxminded.university.domain.timetable.Timetable;

public class JdbcUserQueriesTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcUserQueries jdbcUserQueries;
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
        jdbcUserQueries = new JdbcUserQueries(jdbcTemplate);
    }

    @Test
    @DisplayName("getStudentDayTimetableTest should get Student Day Timetable")
    void getStudentDayTimetableTest_ShouldSelectTimetable() {
        Date date = Date.valueOf("2000-01-01");
        Time startLecture = Time.valueOf("09:00:00");
        Time endLecture = Time.valueOf("10:00:00");
        Timetable timetable = Timetable.builder()
                  .withTimetableId(1)
                  .withDate(date)
                  .withStartLecture(startLecture)
                  .withEndLecture(endLecture)
                  .withLocation("Hall 1")
                  .withGroupId(1)
                  .withSubject("IT")
                  .withTeacherId(1)
                  .build();
          Optional<Timetable> expected = Optional.ofNullable(timetable);
          Optional<Timetable> actual = jdbcUserQueries.getStudentDayTimetable(1, "2022-01-01");
          assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getStudentMonthTimetableTest should Return List of Timetables")
    void getStudentMonthTimetableTest_ShouldReturnSublistOfTimetable() {
        Timetable timetable1 = Timetable.builder()
                  .withTimetableId(1)
                  .withDate(Date.valueOf("2000-01-01"))
                  .withStartLecture(Time.valueOf("09:00:00"))
                  .withEndLecture(Time.valueOf("10:00:00"))
                  .withLocation("Hall 1")
                  .withGroupId(1)
                  .withSubject("IT")
                  .withTeacherId(1)
                  .build();
        Timetable timetable2 = Timetable.builder()
                .withTimetableId(11)
                .withDate(Date.valueOf("2000-01-04"))
                .withStartLecture(Time.valueOf("09:00:00"))
                .withEndLecture(Time.valueOf("10:00:00"))
                .withLocation("Hall 2")
                .withGroupId(1)
                .withSubject("MATH")
                .withTeacherId(4)
                .build();
        List<Timetable> expected = new ArrayList<>();
        expected.add(timetable1);
        expected.add(timetable2);
        List<Timetable> actual = jdbcUserQueries.getStudentMonthTimetable(1, 1).subList(0, 2);
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("getLecturerDayTimetableTest should get Lecturer Day Timetable")
    void getLecturerDayTimetableTest_ShouldSelectTimetable() {
        Timetable timetable = Timetable.builder()
                  .withTimetableId(1)
                  .withDate(Date.valueOf("2000-01-01"))
                  .withStartLecture(Time.valueOf("09:00:00"))
                  .withEndLecture(Time.valueOf("10:00:00"))
                  .withLocation("Hall 1")
                  .withGroupId(1)
                  .withSubject("IT")
                  .withTeacherId(1)
                  .build();
          Optional<Timetable> expected = Optional.ofNullable(timetable);
          Optional<Timetable> actual = jdbcUserQueries.getLecturerDayTimetable(1, "2022-01-01");
          assertThat(actual, equalTo(expected));
    }
    @Test
    @DisplayName("getLecturerMonthTimetableTest should Return subList of Timetables")
    void getLecturerMonthTimetableTest_ShouldReturnListOfTimetable() {
        Timetable timetable1 = Timetable.builder()
                  .withTimetableId(1)
                  .withDate(Date.valueOf("2000-01-01"))
                  .withStartLecture(Time.valueOf("09:00:00"))
                  .withEndLecture(Time.valueOf("10:00:00"))
                  .withLocation("Hall 1")
                  .withGroupId(1)
                  .withSubject("IT")
                  .withTeacherId(1)
                  .build();
        Timetable timetable2 = Timetable.builder()
                .withTimetableId(11)
                .withDate(Date.valueOf("2000-01-04"))
                .withStartLecture(Time.valueOf("09:00:00"))
                .withEndLecture(Time.valueOf("10:00:00"))
                .withLocation("Hall 2")
                .withGroupId(1)
                .withSubject("MATH")
                .withTeacherId(4)
                .build();
        List<Timetable> expected = new ArrayList<>();
        expected.add(timetable1);
        expected.add(timetable2);
        List<Timetable> actual = jdbcUserQueries.getStudentMonthTimetable(1, 1).subList(0, 2);
        assertThat(actual, equalTo(expected));
    }
}
