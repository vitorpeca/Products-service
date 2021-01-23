package com.br.products.controllers;

import com.br.products.domain.model.Product;
import com.br.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value = "Controller Rest Products",tags = { "Products" })
public class ProductController {
    private static final String NOT_EXIST_PRODUCTS = "Not Exists Products In Data Base";
    private static final String NOT_EXIST_PRODUCTS_NAME = "Not Exists Products With name: ";
    private static final String NOT_EXIST_PRODUCTS_ID = "Not Exists Products With Id: ";
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiParam(value = "EndPoint To Create Products.")
    public ResponseEntity<Object> createProducts(@RequestBody @Valid Product product) {
        try {
           Product response = productService.saveProduct(product);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "name/{productName}")
    @ApiParam(value = "EndPoint To Find Product by name.")
    public ResponseEntity<Object> searchProductsByName(@PathVariable("productName") String productName) {
        try {
            Product product = productService.findByName(productName);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(NOT_EXIST_PRODUCTS_NAME + productName,HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/orderedProducts")
    @ApiParam(value = "EndPoint To Find Products and ordered list by name.")
    public ResponseEntity<Object> orderedListByName() {
        try {
            List<Product> products = productService.findAllProducts();
            if (products != null) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(NOT_EXIST_PRODUCTS,HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "id/{productId}")
    @ApiParam(value = "EndPoint To Find Product by Id.")
    public ResponseEntity<Object> searchProductsById(@PathVariable("productId") String productId) {
        try {
            Product product = productService.findById(productId);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(NOT_EXIST_PRODUCTS_ID + productId,HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    @ApiParam(value = "EndPoint To Delete Products")
    public ResponseEntity<Void> deleteProducts(@RequestBody List<@Valid Product> product) {
        try {
            product.parallelStream().forEach(product1 -> productService.deleteProduct(product1));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception exception) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}
