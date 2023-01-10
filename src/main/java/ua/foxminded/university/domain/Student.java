package ua.foxminded.university.domain;

import lombok.*;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "students")
@AttributeOverride(name="firstName", column=@Column(name = "first_name"))
@AttributeOverride(name="lastName", column=@Column(name = "last_name"))
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded = true)
@ToString
public class Student extends Person {
    
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int studentId;
    
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;
}
