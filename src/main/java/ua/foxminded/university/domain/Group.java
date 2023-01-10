package ua.foxminded.university.domain;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.foxminded.university.domain.timetable.Timetable;
import ua.foxminded.university.domain.university.University;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "groups")
@Getter 
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int groupId;
    
    @Column(name = "group_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 3, message = "Name should be between 2 and 4 characters")
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Student> students;
    
    @OneToMany(mappedBy = "group")
    private List<Timetable> timetables;
   
    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    
    
}
