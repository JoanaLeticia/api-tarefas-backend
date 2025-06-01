package com.exemplo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TarefaResourceTest {
    @Test
    public void testListarTarefasEndpoint() {
        given()
                .when().get("/tarefas")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    public void testCriarTarefaEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"titulo\":\"Nova Tarefa\", \"descricao\":\"Teste\", \"status\":\"aberta\"}")
                .when().post("/tarefas")
                .then()
                .statusCode(201)
                .body("titulo", equalTo("Nova Tarefa"));
    }
}
