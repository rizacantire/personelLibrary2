package com.rza.BookSelf.dataAccess;

import com.rza.BookSelf.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Category findCategoryByName(String name);
}
