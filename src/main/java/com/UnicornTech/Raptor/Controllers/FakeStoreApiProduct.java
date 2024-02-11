package com.UnicornTech.Raptor.Controllers;

import com.UnicornTech.Raptor.DTO.FakeApiDto;
import com.UnicornTech.Raptor.ExceptionModel.ExceptionModal;
import com.UnicornTech.Raptor.Models.Product;
import com.UnicornTech.Raptor.Services.ProductInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class FakeStoreApiProduct {

    private  ProductInterface<FakeApiDto> in;
    //Get all products
    public FakeStoreApiProduct(@Qualifier("fakeStoreApiImpl") ProductInterface<FakeApiDto> in){
        this.in = in;
    }
    @GetMapping("all")
    public ResponseEntity<List<FakeApiDto>> getAllProducts(){
        return in.getProducts();
    }

    //Get products by id
    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Integer id) throws ExceptionModal {
        Product product =  in.getProductById(id);
        if(product == null){
            throw new ExceptionModal(HttpStatus.NOT_FOUND,"Object not found");
        }
        return product;
    }

    //Add a product
    @PostMapping("/create")
    public Product createProduct(@RequestBody FakeApiDto dto){
        return in.createProduct(dto);
    }

    //update a product
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable("productId") Integer id , @RequestBody FakeApiDto dto){
        return in.updateProduct(id , dto);
    }

    //Deletes a product

    @DeleteMapping("/delete/{productId}")
    public Product deleteProduct(@PathVariable("productId") Integer id) throws ExceptionModal {
        return in.deleteProduct(id);
    }

    @GetMapping("/all/{limit}")
    public ResponseEntity<List<FakeApiDto>> getLimitedProducts(@PathVariable("limit") Integer limit){
        return in.getLimitedResult(limit);
    }
}
