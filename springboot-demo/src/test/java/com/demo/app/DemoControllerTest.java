package com.demo.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloEndpointReturnsUp() throws Exception {
        mockMvc.perform(get("/api/hello"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void infoEndpointReturnsAppName() throws Exception {
        mockMvc.perform(get("/api/info"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.app").value("springboot-demo"));
    }
}
