package ua.foxminded.university.domain.timetable;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.foxminded.university.domain.Group;
import ua.foxminded.university.domain.Teacher;

@Entity
@Table(name = "timetables")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true)
public class Timetable {
    @Id
    @Column(name = "timetable_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int timetableId;
    
    @Column(name = "timetable_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    
    @Column(name = "start_lecture")
    private Time startLecture;
    
    @Column(name = "end_lecture")
    private Time endLecture;
    
    @Column(name = "location")
    private String location;
    
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;
 
    @Column(name = "subject")
    private String subject;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;
    
    public Timetable(Date date, Time startLecture, Time endLecture, String location, 
            Group group, String subject, Teacher teacher) {
        this.date = date;
        this.startLecture = startLecture;
        this.endLecture = endLecture;
        this.location = location;
        this.group = group;
        this.subject = subject;
        this.teacher = teacher;
    }
    
    
}
