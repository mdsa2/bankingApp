package com.BankingApp.mohammed.Products.Service;

import com.BankingApp.mohammed.Category.dto.categoryRequest;
import com.BankingApp.mohammed.Category.entity.Category;
import com.BankingApp.mohammed.Products.Repositry.productRepositry;
import com.BankingApp.mohammed.Products.dto.ProductMapper;
import com.BankingApp.mohammed.Products.dto.ProductRequest;
import com.BankingApp.mohammed.Products.dto.productdto;
import com.BankingApp.mohammed.Products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductsService {
    private productRepositry productRepositry;
    private ProductMapper productMapper;
    public ProductsService(productRepositry productRepositry, ProductMapper productMapper) {
        this.productRepositry = productRepositry;
        this.productMapper = productMapper;
    }
    public Page<productdto> getAllProducts(Pageable pageable) {
        return productRepositry.findAll(pageable).map(productMapper);
    }
    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setPname(productRequest.getPname());
        product.setPdesc(productRequest.getPdesc());
        product.setPimage(productRequest.getPimage());
        product.setPprice(productRequest.getPprice());
        product.setCategoryId(productRequest.getCategoryId());
        return productRepositry.save(product);


    }
    public Product updateproduct(int id, ProductRequest productRequest) {
        Product product = productRepositry.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));


        product.setPdesc(productRequest.getPname());
        product.setPdesc(productRequest.getPdesc());
        product.setPimage(productRequest.getPimage());
        product.setPprice(productRequest.getPprice());
        product.setCategoryId(productRequest.getCategoryId());
        return productRepositry.save(product);

    }
    public ResponseEntity<Void>  deleteProduct(int id) {

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

