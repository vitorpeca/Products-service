package com.br.products.service;


import com.br.products.domain.model.Product;
import com.br.products.domain.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    public Product saveProduct(Product product){
        return productDAO.saveProduct(product);
    }
    public Product findByName(String name) {
       return productDAO.findByName(name);
    }
    public Product findById(String id){
        return productDAO.findById(id);
    }

    public boolean deleteProduct(Product product){
       return productDAO.deleted(product);
    }

    public List<Product> findAllProducts() {
        List<Product> productList = productDAO.findAllProducts();
        Collections.sort(productList);
        return productList;
    }
}
