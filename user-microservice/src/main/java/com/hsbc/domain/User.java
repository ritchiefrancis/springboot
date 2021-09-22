package com.hsbc.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String tile;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String dob;
    @Column
    private String jobTitle;
    @CreationTimestamp()
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

}
