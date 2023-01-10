package ua.foxminded.university.domain.university;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.*;
import ua.foxminded.university.domain.Teacher;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "university")
@Getter @Setter
@NoArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class University {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int departmentId;

    @Column(name = "department_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 100 characters")
    private String departmentName;
    
    @OneToMany(mappedBy = "department")    
    private List<Teacher> teachers;
    
    public University(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
}