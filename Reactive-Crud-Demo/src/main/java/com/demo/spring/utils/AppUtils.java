package com.demo.spring.utils;

import org.springframework.beans.BeanUtils;

import com.demo.spring.dto.ProductDto;
import com.demo.spring.entity.Product;

public class AppUtils {


    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
    
   // The BeanUtils.copyProperties method is used to copy properties from the Product object to the ProductDto object. 
    //BeanUtils is a utility class provided by the Spring Framework for simplifying the copying of properties between objects.

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

}
