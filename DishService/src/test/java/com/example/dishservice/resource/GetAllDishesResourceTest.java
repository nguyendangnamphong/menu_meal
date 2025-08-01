package com.example.dishservice.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GetAllDishesResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/dishes")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}