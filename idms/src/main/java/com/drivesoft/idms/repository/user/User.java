package com.drivesoft.idms.repository.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column//(name = "username")
    private String username;

    @JsonIgnore
    @Column
    private String password;

}

