package com.BankingApp.mohammed.Category.Service;

import com.BankingApp.mohammed.Category.Repositry.CategoryRepositry;
import com.BankingApp.mohammed.Category.dto.CategoryMapper;
import com.BankingApp.mohammed.Category.dto.categoryRequest;
import com.BankingApp.mohammed.Category.dto.categorydto;
import com.BankingApp.mohammed.Category.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class CategoryService {
    private CategoryRepositry categoryRepositry;
    private CategoryMapper categoryMapper;
    public CategoryService(CategoryRepositry categoryRepositry, CategoryMapper categoryMapper) {
        this.categoryRepositry = categoryRepositry;
           this.categoryMapper = categoryMapper;
    }

    public Page<categorydto> getAllCategories(Pageable pageable) {
        return categoryRepositry.findAll(pageable)
                .map(categoryMapper);
    }
        public Category addcategory(categoryRequest category) {
        Category categoryEntity = new Category();
        categoryEntity.setCname(category.getCname());
        return categoryRepositry.save(categoryEntity);
    }

    public Category updateCategory(int id, categoryRequest categoryRequest) {
         Category categoryEntity = categoryRepositry.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));


        categoryEntity.setCname(categoryRequest.getCname());

        return categoryRepositry.save(categoryEntity);
    }

    public ResponseEntity<Void> deleteCategory( int id) {
        if (!categoryRepositry.existsById(id)) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            categoryRepositry.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
