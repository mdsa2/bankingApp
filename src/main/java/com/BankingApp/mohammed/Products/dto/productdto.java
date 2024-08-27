package com.BankingApp.mohammed.Products.dto;

import com.BankingApp.mohammed.Category.dto.categorydto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productdto {
    private int id;
    private String Pname;
    private String Pdesc;
    private String Pprice;
    private String Pimage;
    private String categoryname;
}
