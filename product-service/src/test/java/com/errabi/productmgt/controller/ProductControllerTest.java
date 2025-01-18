package com.errabi.productmgt.controller;

import com.errabi.productservice.enums.Category;
import com.errabi.productservice.enums.Status;
import com.errabi.productservice.web.dtos.ProductDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest extends BaseControllerIT {
    @Test
    @Order(1)
    void CreateProductOkTest() throws Exception {
        mockMvc.perform(post("/api/products")
                        .content(asJsonString(ProductDTO.builder()
                                .name("Product 1")
                                .price(BigDecimal.valueOf(100.0))
                                .category(Category.CLOTHING)
                                .status(Status.AVAILABLE)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void FindProductByIdOkTest() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void FindAllProductsOkTest() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void UpdateProductOkTest() throws Exception {
        mockMvc.perform(put("/api/products/1")
                        .content(asJsonString(ProductDTO.builder()
                                .name("Product Updated")
                                .price(BigDecimal.valueOf(200.0))
                                .category(Category.BEAUTY)
                                .status(Status.OUT_OF_STOCK)
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void DeleteProductOkTest() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}