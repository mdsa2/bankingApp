package com.BankingApp.mohammed.Products.entity;

import com.BankingApp.mohammed.Category.entity.Category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Pname;
    private String Pdesc;
    private String Pprice;
    private String Pimage;
    private int CategoryId;
    @ManyToOne
    @JoinColumn()
    private Category Catogries;

}
