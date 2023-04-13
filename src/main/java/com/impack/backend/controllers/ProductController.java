package com.impack.backend.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impack.backend.dto.ProductData;
import com.impack.backend.dto.ResponseData;
import com.impack.backend.models.Product;
import com.impack.backend.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired private ProductService productService;
    @Autowired private ModelMapper mapper;
    @CrossOrigin
    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Integer id) {
        return productService.findOne(id);
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne (@PathVariable("id") Integer id){
        ResponseData<String> responseData =new ResponseData<>();
        productService.removeOne(id);
        responseData.setStatus(true);
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    } 
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ResponseData<Product>> create (@Valid @RequestBody ProductData productData, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
      
        Product product = mapper.map(productData,Product.class);
        responseData.setStatus(true);
        responseData.setData(productService.save(product));
        return ResponseEntity.ok(responseData);
    }
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ResponseData<Product>> update (@Valid @RequestBody ProductData productData, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Product product = mapper.map(productData,Product.class);
        responseData.setStatus(true);
        responseData.setData(productService.save(product));
        return ResponseEntity.ok(responseData);
    }
}
