package majescoTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ReadProperties;
import utils.extentReportsUtils.ExtentTestManager;
import java.lang.reflect.Method;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class MajescoAPITest {
    private static final Logger logger = LoggerFactory.getLogger(MajescoAPITest.class);

    @Test(priority = 0, description="Get the value for API Activity", groups="API")
    public void getKey(Method method) throws JsonProcessingException {
        ExtentTestManager.startTest(method.getName(), "Get the value for API Activity");
        Response response = given()
                .when()
                .get(ReadProperties.readConfigProperty("APIBuildURI"));
        logger.info("Validating the Response Status Code");
        Assert.assertEquals("200", String.valueOf(response.statusCode()), "Response Status Code is 200");
        logger.info("Validating the Response Status Line");
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine(), "Response Status Line is HTTP/1.1 200 OK");

// AS THIS IS A DYNAMIC RESPONSE I CANNOT ASSERT WITH THE VALUES HENCE ONLY PRINTING THE VALUES.

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(response.prettyPrint());
        Iterator fieldValues = jsonTree.elements();
        Iterator fieldName = jsonTree.fieldNames();
        while(fieldValues.hasNext()) {
            logger.info("Response Value for the attribute - "+fieldName.next() +" : "+ fieldValues.next());
        }
    }
}
