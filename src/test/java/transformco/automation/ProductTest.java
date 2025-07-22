package transformco.automation;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static transformco.factory.ProductDataFactory.newProduct;
import transformco.dto.ProductDTO;

public class ProductTest extends AbstractTesterHelper {

    @Test
    @DisplayName("Create new product")
    public void createNewProduct() throws IOException {
        setUpHeader(token, "creatingNewProduct")
            .filter(new AllureRestAssured())
            .given()
                .body(newProduct())
            .when()
                .post("/produtos")
            .then()
                .assertThat()
                    .statusCode(201)
                    .body("message", containsString("Cadastro realizado com sucesso"));
    }

    @Test
    @DisplayName("Get product by ID")
    public void getProductById() throws IOException{
        ProductDTO product = newProduct();
        
        String productId = setUpHeader(token,
                    "creatingNewProduct-toGetById")
            .filter(new AllureRestAssured())
            .given()
                .body(product)
            .when()
                .post("/produtos")
            .then()
                .assertThat()
                    .statusCode(201)
                    .body("message", containsString("Cadastro realizado com sucesso"))
                .extract()
                    .path("_id");

        setUpHeader(token, "getProductById")
            .filter(new AllureRestAssured())
            .given()
            .when()
                .get("/produtos/" + productId)
            .then()
                .assertThat()
                    .statusCode(200)
                    .body("nome", equalTo(product.getNome()))
                    .body("_id", equalTo(productId));
    }
}
