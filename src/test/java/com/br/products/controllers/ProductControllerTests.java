package com.br.products.controllers;


import com.br.products.domain.model.Product;
import com.br.products.service.ProductService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ProductService service;

    private final Product mockedProducts = new Product("1", "SKOL", "Cerveja de cevada", BigDecimal.ONE, "SKOL");
    private Gson gson = new Gson();
    private final String json = gson.toJson(mockedProducts);

    @Before
    public void setup() {
        final ProductController controller = new ProductController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).addInterceptors().build();
    }

    @Test
    public void getProductsFromOrdered() throws Exception {
        when(service.findAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products/orderedProducts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getProductFromProductIdSuccess() throws Exception {
        when(service.findById("1")).thenReturn(mockedProducts);
        mockMvc.perform(get("/products/id/{productId}", "1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void getProductFromProductNameSuccess() throws Exception {
        when(service.findByName("SKOL")).thenReturn(mockedProducts);
        mockMvc.perform(get("/products/name/{productName}", "SKOL").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void postProductFromProductSuccess() throws Exception {
        mockMvc.perform(post("/products").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void deletedProductFromProductSuccess() throws Exception {
        mockMvc.perform(delete("/products").content(gson.toJson(Arrays.asList(mockedProducts))).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
    }

}
