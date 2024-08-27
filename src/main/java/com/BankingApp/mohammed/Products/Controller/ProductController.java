package com.BankingApp.mohammed.Products.Controller;

import com.BankingApp.mohammed.Category.dto.categoryRequest;
import com.BankingApp.mohammed.Category.dto.categorydto;
import com.BankingApp.mohammed.Category.entity.Category;
import com.BankingApp.mohammed.Products.Repositry.productRepositry;
import com.BankingApp.mohammed.Products.Service.ProductsService;
import com.BankingApp.mohammed.Products.dto.ProductRequest;
import com.BankingApp.mohammed.Products.dto.productdto;
import com.BankingApp.mohammed.Products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Products")
public class ProductController {
    private final com.BankingApp.mohammed.Products.Repositry.productRepositry productRepositry;
    private ProductsService productsService;
    public ProductController(ProductsService productsService, productRepositry productRepositry) {
        this.productsService = productsService;
        this.productRepositry = productRepositry;
    }
    @GetMapping("GetProducts")

    public Page<productdto> getAllCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort) {


        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;
        String defaultSort = (sort != null) ? sort : "id";

        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by(defaultSort));

        return productsService.getAllProducts(pageable);
    }
    @PostMapping("AddProducts")
    public Product createProduct(ProductRequest productRequest) {
        return productsService.addProduct(productRequest);
    }
    @PutMapping("/{id}")
    public Product updateCategory(@PathVariable int id,@RequestBody ProductRequest productRequest) {
        return productsService.updateproduct(id,productRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory( int id) {
        if (!productRepositry.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            productRepositry.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
