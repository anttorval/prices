package org.example.prices.infrastructure.adapters.input.rest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PriceIntegrationTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/v1/prices")
                        .param("date", "14/06/2020 10:00:00")
                        .param("product-id", String.valueOf(35455))
                        .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(String.valueOf(35455)))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(String.valueOf(1)))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value("14/06/2020 00:00:00"))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value("31/12/2020 23:59:59"))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value("35.5"))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andDo(print());
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/v1/prices")
                        .param("date", "14/06/2020 16:00:00")
                        .param("product-id", String.valueOf(35455))
                        .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(String.valueOf(35455)))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(String.valueOf(2)))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value("14/06/2020 15:00:00"))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value("14/06/2020 18:30:00"))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value("25.45"))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andDo(print());
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test3() throws Exception {
        mockMvc.perform(get("/v1/prices")
                        .param("date", "14/06/2020 21:00:00")
                        .param("product-id", String.valueOf(35455))
                        .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(String.valueOf(35455)))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(String.valueOf(1)))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value("14/06/2020 00:00:00"))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value("31/12/2020 23:59:59"))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value("35.5"))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andDo(print());
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test4() throws Exception {
        mockMvc.perform(get("/v1/prices")
                        .param("date", "15/06/2020 10:00:00")
                        .param("product-id", String.valueOf(35455))
                        .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(String.valueOf(35455)))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(String.valueOf(3)))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value("15/06/2020 00:00:00"))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value("15/06/2020 11:00:00"))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value("30.5"))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andDo(print());
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test5() throws Exception {
        mockMvc.perform(get("/v1/prices")
                        .param("date", "16/06/2020 21:00:00")
                        .param("product-id", String.valueOf(35455))
                        .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(String.valueOf(35455)))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(String.valueOf(1)))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(String.valueOf(4)))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value("15/06/2020 16:00:00"))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value("31/12/2020 23:59:59"))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value("38.95"))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andDo(print());
    }
}
