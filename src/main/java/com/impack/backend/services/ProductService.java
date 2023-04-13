package com.impack.backend.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impack.backend.models.Product;
import com.impack.backend.repositories.ProductRepo;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public Product save(Product product){
        return productRepo.save(product);
    }
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }
    public Product findOne(Integer id){
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            throw new RuntimeException(
                    String.format("Product with Id '%s' Not exists", id)
                );
        }
        return product.get();
    }
    public void removeOne(Integer id) {
        productRepo.deleteById(id);
    }
}
