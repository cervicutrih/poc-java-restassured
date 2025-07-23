package transformco.automation;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.*;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static transformco.factory.ProductDataFactory.newProduct;
import transformco.dto.ProductDTO;

@Epic("Products API")
@Feature("Product Management")
public class ProductTest extends AbstractTesterHelper {

    @Test
    @DisplayName("Create new product")
    @Story("Product Creation")
    @Description("Test to verify successful creation of a new product")
    @Severity(SeverityLevel.CRITICAL)
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
    @Story("Product Retrieval")
    @Description("Test to verify successful retrieval of a product by its ID")
    @Severity(SeverityLevel.CRITICAL)
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
