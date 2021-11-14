package com.rza.BookSelf.business.concretes;

import com.rza.BookSelf.business.abstracts.CategoryService;
import com.rza.BookSelf.dataAccess.CategoryDao;
import com.rza.BookSelf.entities.concretes.Category;
import com.rza.BookSelf.entities.concretes.UsersBooks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAlll() {
        return this.categoryDao.findAll();
    }

    @Override
    public boolean add(Category entity) {
        var c = this.categoryDao.findCategoryByName(entity.getName());
        if (c == null) {
            this.categoryDao.save(entity);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean update(Category entity) {
        this.categoryDao.save(entity);
        return true;
    }

    @Override
    public void delete(Category entity) {

        this.categoryDao.delete(entity);
    }

    @Override
    public boolean deleteById(int id) {

        this.categoryDao.deleteById(id);
        return true;
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return this.categoryDao.findCategoryByName(categoryName);
    }
}
