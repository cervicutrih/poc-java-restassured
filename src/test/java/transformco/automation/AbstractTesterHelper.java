package transformco.automation;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import transformco.config.ConfigProperties;
import transformco.utils.GenericTestsUtils;

import static io.restassured.RestAssured.*;

public class AbstractTesterHelper extends GenericTestsUtils {
    protected static String token;
    private static final String jsonBody = "{ \"email\": \"test1h@test.com\", \"password\": \"test\" }";

    @BeforeAll
    public static void setUpToken() {
        token = given()
                    .contentType(ContentType.JSON)
                    .header("requestTraceId",
                        "generatingAutomationToken")
                    .body(jsonBody)
                .when()
                    .post(ConfigProperties.getTokenUri())
                .then()
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
