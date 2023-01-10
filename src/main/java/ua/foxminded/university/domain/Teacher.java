package ua.foxminded.university.domain;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.foxminded.university.domain.timetable.Timetable;
import ua.foxminded.university.domain.university.University;

@Entity
@Table(name = "teachers")
@AttributeOverride(name="firstName", column=@Column(name = "first_name"))
@AttributeOverride(name="lastName", column=@Column(name = "last_name"))
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true)
public class Teacher extends Person {    
    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int teacherId;
    

    
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private University department;
    
    @Column(name = "position")
    private String position;    
    
    @OneToMany(mappedBy = "teacher")
    List<Timetable> timetables;
    
}
