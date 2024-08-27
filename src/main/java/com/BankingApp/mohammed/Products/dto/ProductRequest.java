package com.BankingApp.mohammed.Products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String Pname;
    private String Pdesc;
    private String Pprice;
    private String Pimage;
    private int CategoryId;
}
