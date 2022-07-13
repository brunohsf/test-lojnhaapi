package modulos.produto;


import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import static dataFactory.ProdutoDataFactory.*;
import static dataFactory.UsuarioDataFactory.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@DisplayName("Testes de API Rest do módulo de produto")
public class ProdutoTest {

    private String token;

    @BeforeEach
    public void beforeEach() {
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        // port = 8080;
        basePath = "/lojinha";

        // Obter o token do usuário admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(CriarUsuarioAdmin())
                .when()
                .post("/v2/login")
                .then()
                .extract()
                .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto 0.00 não é permitido")
    public void testValidarLimiteZeradoProibidoValorProduto() {

        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        //E o status code retornado foi 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(criarProdutoComumComOValorIgualA(0.00))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto 7000.01 não é permitido")
    public void testValidarLimiteMaiorQueSeteMiloibidosValorProduto() {

        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        //E o status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(criarProdutoComumComOValorIgualA(7000.01))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
}
