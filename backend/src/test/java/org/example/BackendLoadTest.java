package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendLoadTest {
    @Test
    void contextLoads() {
        // If the DB connection, MQTT, or Flyway fails, 
        // this test throws an exception and the build fails.
    }
}
