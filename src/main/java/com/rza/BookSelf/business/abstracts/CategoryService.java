package com.rza.BookSelf.business.abstracts;

import com.rza.BookSelf.entities.concretes.Category;

public interface CategoryService extends EntityService<Category> {
    boolean deleteById(int id);

    Category findByCategoryName(String categoryName);
}
