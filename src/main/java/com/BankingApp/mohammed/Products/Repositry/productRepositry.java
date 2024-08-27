package com.BankingApp.mohammed.Products.Repositry;

import com.BankingApp.mohammed.Products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepositry extends JpaRepository<Product, Integer> {
}
