package com.UnicornTech.Raptor.Models;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;
}
