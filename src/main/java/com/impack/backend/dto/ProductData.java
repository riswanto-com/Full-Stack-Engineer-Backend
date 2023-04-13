package com.impack.backend.dto;

import javax.validation.constraints.NotEmpty;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductData {
    private Integer id;
    @NotEmpty(message = "produk code tidak boleh kosong")
    private String productCode;    
    @NotEmpty(message = "produk nama tidak boleh kosong")
    private String productName; 
    @NotEmpty(message = "produk diskripsi tidak boleh kosong")
    private String productDescription;   
    private Double productPrice;
    @NotEmpty(message = "produk uom tidak boleh kosong")
    private String productUOM;    

}
