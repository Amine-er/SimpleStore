package com.errabi.productmgt.controller;

import com.errabi.reviewservice.ReviewServiceApplication;
import com.errabi.reviewservice.web.dtos.ReviewDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ReviewServiceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewControllerTest extends BaseControllerIT {

    @Test
    @Order(1)
    void CreateReviewOkTest() throws Exception {
        mockMvc.perform(post("/api/reviews")
                        .content(asJsonString(ReviewDto.builder()
                                .productId(1L)
                                .rating(4)
                                .content("Great product!")
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void FindReviewByProductIdOkTest() throws Exception {
        mockMvc.perform(get("/api/reviews/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void UpdateReviewOkTest() throws Exception {
        mockMvc.perform(put("/api/reviews/1")
                        .content(asJsonString(ReviewDto.builder()
                                .rating(5)
                                .content("Updated comment!")
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void DeleteReviewOkTest() throws Exception {
        mockMvc.perform(delete("/api/reviews/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
