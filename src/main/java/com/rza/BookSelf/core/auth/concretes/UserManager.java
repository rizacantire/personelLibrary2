package com.rza.BookSelf.core.auth.concretes;

import com.rza.BookSelf.core.auth.abstracts.UserService;
import com.rza.BookSelf.core.dataAccess.UserDao;
import com.rza.BookSelf.core.entities.User;
import com.rza.BookSelf.entities.concretes.UsersBooks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAlll() {
        return this.userDao.findAll();
    }

    @Override
    public boolean add(User entity) {
        var result = this.userDao.findByMail(entity.getMail());
        if (result.toString().isEmpty()) {
            this.userDao.save(entity);
            System.out.println("Başarılı");
            return true;
        } else {
            System.out.println("Mail mevcut");
            return false;
        }
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
