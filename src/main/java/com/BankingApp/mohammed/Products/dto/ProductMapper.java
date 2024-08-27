package com.BankingApp.mohammed.Products.dto;

import com.BankingApp.mohammed.Category.entity.Category;
import com.BankingApp.mohammed.Products.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductMapper implements Function<Product,productdto> {
    @Override
    public productdto apply(Product product) {
        productdto dto = new productdto();
        dto.setId(product.getId());

        dto.setPname(product.getPname());
        dto.setPdesc(product.getPdesc());
        dto.setPimage(product.getPimage());
        dto.setPprice(product.getPprice());

        if (product.getCatogries() != null) {
            dto.setCategoryname(product.getCatogries().getCname());
        }
        return dto;
    }
}
