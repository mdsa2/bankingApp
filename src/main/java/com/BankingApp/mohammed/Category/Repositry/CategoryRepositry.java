package com.BankingApp.mohammed.Category.Repositry;

import com.BankingApp.mohammed.Category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositry extends JpaRepository<Category, Integer> {
}
