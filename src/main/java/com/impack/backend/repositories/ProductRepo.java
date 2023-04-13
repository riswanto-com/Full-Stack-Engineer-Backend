package com.impack.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impack.backend.models.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{
    
    
}
