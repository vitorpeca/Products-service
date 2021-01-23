package com.br.products.service;

import com.br.products.domain.dao.ProductDAO;
import com.br.products.domain.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;
    @Mock
    protected ProductDAO productDAO;

    @Test
    public void findById() {
        Mockito.when(productDAO.findById("1")).thenReturn(mockedProducts);
        Product response = productService.findById("1");
        assertEquals("SKOL", response.getName());
        assertEquals("1", response.getProductId());
        assertEquals(BigDecimal.ONE, response.getPrice());
        assertEquals("SKOL", response.getBrand());
    }

    @Test
    public void findByName() {
        Mockito.when(productDAO.findByName("SKOL")).thenReturn(mockedProducts);
        Product response = productService.findByName("SKOL");
        assertEquals("SKOL", response.getName());
        assertEquals("1", response.getProductId());
        assertEquals(BigDecimal.ONE, response.getPrice());
        assertEquals("SKOL", response.getBrand());
    }

    @Test
    public void findByOrdered() {
        Mockito.when(productDAO.findAllProducts()).thenReturn(Arrays.asList(mockedProducts,mockedProducts2));
        List<Product> response = productService.findAllProducts();
        assertEquals("Brahma", response.get(0).getName());
        assertEquals("2", response.get(0).getProductId());
        assertEquals(BigDecimal.TEN, response.get(0).getPrice());
        assertEquals("Brahma", response.get(0).getBrand());
        assertEquals(2,response.size());
    }

    @Test
    public void saveProduct(){
        Mockito.when(productDAO.saveProduct(mockedProducts)).thenReturn(mockedProducts);
        Product response = productService.saveProduct(mockedProducts);
        assertEquals(mockedProducts,response);
    }

    @Test
    public void deleteSucess() {
        Mockito.when(productDAO.deleted(mockedProducts)).thenReturn(true);
        boolean response = productService.deleteProduct(mockedProducts);
        assertEquals(true, response);
    }

    Product mockedProducts = new Product("1", "SKOL", "Cerveja de cevada", BigDecimal.ONE, "SKOL");
    Product mockedProducts2 = new Product("2", "Brahma", "Cerveja de cevada", BigDecimal.TEN, "Brahma");
}
