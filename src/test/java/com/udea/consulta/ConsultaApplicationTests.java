package com.udea.consulta;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConsultaApplicationTests {

    @Autowired
    private DataController dataController;

    @Test
    void health() {
        assertEquals("HEALTH CHECK OK!", dataController.healthCheck());
    }

    @Test
    void version() {
        assertEquals("The actual version is 1.0.0", dataController.version());
    }

    @Test
    void nationLength() {
        Integer nationsLength = dataController.getRandomNations().size();
        assertEquals(10, nationsLength);
    }

    @Test
    void currenciesLength() {
        Integer currenciesLength = dataController.getRandomCurrencies().size();
        assertEquals(20, currenciesLength);
    }

    @Test
    void aviationsLength() {
        Integer aviationsLength = dataController.getRandomnAviation().size();
        assertEquals(20, aviationsLength);
    }

    @Test
    void randomCurrenciesCodeFormat() {
        JsonNode response = dataController.getRandomCurrencies();
        for (int i = 0; i < response.size(); i++) {
            JsonNode currency = response.get(i);
            String code = currency.get("code").asText();
            assertTrue(code.matches("[A-Z]{3}"));
        }
    }

    @Test
    void randomNationsPerformance() {
        long startTime = System.currentTimeMillis();
        dataController.getRandomNations();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime < 2000);
    }

    @Test
    void aviationPayloadHasExpectedFields() {
        JsonNode response = dataController.getRandomnAviation();
        JsonNode firstAviation = response.get(0);

        assertFalse(firstAviation.get("aircraft").asText().isBlank());
        assertFalse(firstAviation.get("airport").asText().isBlank());
        assertFalse(firstAviation.get("METAR").asText().isBlank());
    }
}
