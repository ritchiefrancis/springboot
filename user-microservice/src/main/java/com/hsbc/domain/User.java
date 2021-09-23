package com.hsbc.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column
    private String tile;
    @NotBlank(message = "First name is mandatory")
    @Column
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Column
    private String lastName;
    @NotBlank(message = "DOB is mandatory")
    @Column
    private String dob;
    @NotBlank(message = "Job Title is mandatory")
    @Column
    private String jobTitle;
    @CreationTimestamp()
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

}
