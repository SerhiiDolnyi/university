package ua.foxminded.university.domain;

import lombok.*;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    protected String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    protected String lastName;

    @Column(name = "contact")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    protected String contact;
}