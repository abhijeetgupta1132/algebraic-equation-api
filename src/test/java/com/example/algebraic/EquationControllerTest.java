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
class EquationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void storeEquation_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/equations/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"equation\":\"3*x+2*y-z\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void evaluateValidEquation_shouldReturnOk() throws Exception {

        // store equation first
        mockMvc.perform(post("/api/equations/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"equation\":\"3*x+2*y-z\"}"));

        // evaluate
        mockMvc.perform(post("/api/equations/1/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"variables\":{\"x\":2,\"y\":1,\"z\":3}}"))
                .andExpect(status().isOk());
    }

    @Test
    void evaluateMissingVariable_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(post("/api/equations/store")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"equation\":\"x+y\"}"));

        mockMvc.perform(post("/api/equations/1/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"variables\":{\"x\":2}}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void evaluateInvalidEquationId_shouldReturnNotFound() throws Exception {

        mockMvc.perform(post("/api/equations/99/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"variables\":{\"x\":1}}"))
                .andExpect(status().isNotFound());
    }
}
