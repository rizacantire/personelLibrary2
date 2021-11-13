package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.UsersBooksService;
import com.rza.BookSelf.core.auth.abstracts.UserService;
import com.rza.BookSelf.core.entities.User;
import com.rza.BookSelf.dataAccess.UsersBookDao;
import com.rza.BookSelf.entities.concretes.UsersBooks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersBooksManager implements UsersBooksService {
    private UsersBookDao usersBookDao;

    public UsersBooksManager(UsersBookDao usersBookDao) {
        this.usersBookDao = usersBookDao;
    }


    @Override
    public List<UsersBooks> getAlll() {
        return this.usersBookDao.findAll();
    }

    @Override
    public boolean add(UsersBooks entity) {
        this.usersBookDao.save(entity);
        return true;
    }

    @Override
    public boolean update(UsersBooks entity) {
        return false;
    }

    @Override
    public void delete(UsersBooks entity) {

    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void addBooks(UsersBooks usersBooks) {
        this.usersBookDao.save(usersBooks);
    }

    @Override
    public List<UsersBooks> getByUserId(int id) {
        return this.usersBookDao.findByCustomerUserId(id);
    }
}
