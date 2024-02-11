package com.UnicornTech.Raptor.Services;

import com.UnicornTech.Raptor.DTO.FakeApiDto;
import com.UnicornTech.Raptor.ExceptionModel.NotFoundException;
import com.UnicornTech.Raptor.Models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInterface<T> {
     ResponseEntity<List<T>> getProducts();
     ResponseEntity<List<T>> getLimitedResult(Integer limit);
     ResponseEntity<List<T>> getSortedResult(String sortOrder);
     Product createProduct(T body);
     Product updateProduct(Integer productId , T body);
     Product deleteProduct(Integer productId);
     Product getProductById(Integer productId);
}
