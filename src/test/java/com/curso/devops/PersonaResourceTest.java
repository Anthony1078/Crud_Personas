package com.curso.devops;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PersonaResourceTest {

    @Test
    void testCreatePersona() {
        String personaJson = """
        {
            "nombre": "Wilder Anthony",
            "apellido": "Guizado Romero",
            "direccion": "S.J.M",
            "telefono": "999999999"
        }
        """;

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaJson)
                .when().post("/persona/api")
                .then()
                .statusCode(201)
                .body("nombre", is("Wilder Anthony"),
                        "apellido", is("Guizado Romero"),
                        "direccion", is("S.J.M"),
                        "telefono", is("999999999"));
    }


    @Test
    void testUpdatePersona() {
        String updatedPersonaJson = """
        {
            "id": 11,
            "nombre": "Wilder",
            "apellido": "Guizado Romero",
            "direccion": "S.J.M",
            "telefono": "999999999"
        }
        """;

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedPersonaJson)
                .when().put("/persona/api")
                .then()
                .statusCode(200)
                .body("id", is(11),
                        "nombre", is("Wilder"),
                        "apellido", is("Guizado Romero"),
                        "direccion", is("S.J.M"),
                        "telefono", is("999999999"));
    }


//    @Test
//    void testGetPersonaById() {
//        given()
//                .when().get("/persona/api?id=11")
//                .then()
//                .statusCode(200)
//                .body("id", is(11),
//                        "nombre", is("Wilder"),
//                        "apellido", is("Guizado Romero"),
//                        "direccion", is("S.J.M"),
//                        "telefono", is("999999999"));
//    }


    @Test
    void testDeletePersona() {
        given()
                .when().delete("/persona/api/11")
                .then()
                .statusCode(204);
    }

}