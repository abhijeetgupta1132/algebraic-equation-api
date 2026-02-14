package com.example.algebraic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EquationControllerErrorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluate_missingVariable_shouldReturnBadRequest() throws Exception {

        // First store equation
        mockMvc.perform(post("/api/equations/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"equation\":\"3x+2y-z\"}"))
                .andExpect(status().isOk());

        // Evaluate without variable 'z'
        mockMvc.perform(post("/api/equations/1/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"variables\":{\"x\":2,\"y\":1}}"))
                .andExpect(status().isBadRequest());
    }
}
