package com.rza.BookSelf.entities.concretes;


import com.rza.BookSelf.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Customer extends User {

    @OneToMany
    private List<UsersBooks> usersBooks;
}
