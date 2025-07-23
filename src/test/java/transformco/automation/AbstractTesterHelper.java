package transformco.automation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import transformco.config.ConfigProperties;
import transformco.utils.GenericTestsUtils;

import static io.restassured.RestAssured.*;

public class AbstractTesterHelper extends GenericTestsUtils {
    protected static String token;
    private static final String jsonBody = String.format("{ \"email\": \"%s\", \"password\": \"%s\" }",
        ConfigProperties.getClientId(), ConfigProperties.getClientPass());

    @BeforeAll
    public static void setUpToken() {
        Response response = given()
                    .contentType(ContentType.JSON)
                    .header("requestTraceId",
                        "generatingAutomationToken")
                    .body(jsonBody)
                .when()
                    .post(ConfigProperties.getTokenUri());
        
        token = response.then()
                    .extract()
                        .path("authorization");
    }

    protected RequestSpecification setUpHeader(
            String token, String testName) {
        baseURI = ConfigProperties.getBaseUri();
//        basePath = ConfigProperties.getBasePath();

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token);
    }
}
