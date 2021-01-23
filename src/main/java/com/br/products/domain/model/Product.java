package com.br.products.domain.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class Product implements Comparable<Product>{

    @Id
    @NotEmpty(message = "Product id cannot be null or empty")
    @ApiModelProperty(position = 0, notes = "The productId", required = true, example = "1")
    private String productId;
    @NotEmpty(message = "Product name cannot be null or empty")
    @ApiModelProperty(position = 1, notes = "The product name", required = true, example = "Skol")
    private String name;
    @NotEmpty(message = "Product Description cannot be null or empty")
    @ApiModelProperty(position = 2, notes = "The product description", required = true, example = "Cerveja de cevada")
    private String description;
    @NotNull(message = "Product price cannot be null")
    @ApiModelProperty(position = 3, notes = "The productId", required = true, example = "2.5")
    private BigDecimal price;
    @NotEmpty(message = "Product brand cannot be null or empty")
    @ApiModelProperty(position = 4, notes = "The product brand", required = true, example = "Skol")
    private String brand;

    public Product() {
        super();
    }

    public Product(@NotEmpty String productId, @NotEmpty String name, @NotEmpty String description, @NotEmpty BigDecimal price, @NotEmpty String brand) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int compareTo(Product other) {
        return name.compareTo(other.name);
    }
}
