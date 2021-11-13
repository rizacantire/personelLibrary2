package com.rza.BookSelf.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Email
    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "mail")
    private String mail;

    @NotEmpty
    @NotBlank
    @NotNull
    @Column(name = "password")
    private String password;


}
