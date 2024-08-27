package com.BankingApp.mohammed.Category.dto;


import com.BankingApp.mohammed.Category.entity.Category;
import com.BankingApp.mohammed.User.Entity.user;
import com.BankingApp.mohammed.User.dto.GetAll;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Component
public class CategoryMapper implements Function<Category, categorydto> {
    @Override
    public categorydto apply(Category category) {

        return new categorydto(category.getId(), category.getCname());
    }

}
