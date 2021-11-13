package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.BookService;
import com.rza.BookSelf.dataAccess.BookDao;
import com.rza.BookSelf.entities.concretes.Author;
import com.rza.BookSelf.entities.concretes.Book;
import com.rza.BookSelf.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {

    @Autowired
    private BookDao bookDao;

    public BookManager(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookManager() {

    }

    @Override
    public List<Book> getAlll() {
        return this.bookDao.findAll();
    }


    @Override
    public boolean add(Book entity) {
        var result = this.bookDao.findBookByName(entity.getName()).stream().filter(
                b -> b.getName().equals(entity.getName())
        ).findAny();

        if (result.isEmpty()) {
            this.bookDao.save(entity);
            System.out.println("başarılı");
            return true;
        } else {
            System.out.println("kitap mevcut");
            return false;
        }

    }

    @Override
    public boolean update(Book entity) {
        this.bookDao.save(entity);
        return true;
    }

    @Override
    public void delete(Book entity) {
        this.bookDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {
        this.bookDao.deleteById(id);
        return true;
    }

    @Override
    public List<Book> getBookByCategory(List<Category> categoryName) {
        return this.bookDao.findBooksByCategoriesIn(categoryName);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return this.bookDao.getBookByAuthors(author);
    }
}
