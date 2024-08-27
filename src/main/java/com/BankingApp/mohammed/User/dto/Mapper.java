package com.BankingApp.mohammed.User.dto;

import com.BankingApp.mohammed.User.Entity.user;

public class Mapper {

    public static GetAll toGetAll(user user) {
        GetAll dto = new GetAll();
        dto.setId(user.getId());

        dto.setFirstName(user.getFirstName());
        dto.setLastname(user.getLastName());
        dto.setEmail(user.getEmail());

        return dto;
    }

}

