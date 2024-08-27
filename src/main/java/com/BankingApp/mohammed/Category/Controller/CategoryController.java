package com.BankingApp.mohammed.Category.Controller;

import com.BankingApp.mohammed.Category.Service.CategoryService;
import com.BankingApp.mohammed.Category.dto.categoryRequest;
import com.BankingApp.mohammed.Category.dto.categorydto;

import com.BankingApp.mohammed.Category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public Page<categorydto> getAllCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort) {


        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;
        String defaultSort = (sort != null) ? sort : "id";

        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by(defaultSort));

        return categoryService.getAllCategories(pageable);
    }
    @PostMapping
    public Category createCategory(@RequestBody categoryRequest categoryRequest) {
        return categoryService.addcategory(categoryRequest);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id,@RequestBody categoryRequest categoryRequest) {
        return categoryService.updateCategory(id,categoryRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
