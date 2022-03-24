package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification reqspec;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String petId, orderId;


    @Given("Pet is created with {string} {string} {string}")
    public void pet_is_created_with(String id, String name, String status) throws IOException {
        reqspec = given().spec(requestSpecification()).body(data.addPet(id, name, status));
        petId = id;
    }

    @When("user calls {string} with {string} https request")
    public void user_calls_with_https_request(String resource, String httpMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("POST"))
            response = reqspec.when().post(resourceAPI.getResource());

        switch (resource) {
            case ("petAPI"):
                if (httpMethod.equalsIgnoreCase("GET"))
                    response = reqspec.when().get(resourceAPI.getResource() + "/" + petId);
                else if (httpMethod.equalsIgnoreCase("DELETE"))
                    response = reqspec.when().delete(resourceAPI.getResource() + "/" + petId);
                break;
            case ("storeAPI"):
                if (httpMethod.equalsIgnoreCase("GET"))
                    response = reqspec.when().get(resourceAPI.getResource() + "/" + orderId);
                else if (httpMethod.equalsIgnoreCase("DELETE"))
                    response = reqspec.when().delete(resourceAPI.getResource() + "/" + orderId);
                break;
        }
    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int code) {
        assertEquals(code, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Given("there is pet with {string}")
    public void thereIsPetWith(String id) throws IOException {
        petId = id;
        reqspec = given().spec(requestSpecification());
    }

    @And("user places new order with {string} {string} {string}")
    public void userPlacesNewOrderWith(String orderId, String petId, String quantity) throws IOException {
        reqspec = given().spec(requestSpecification()).body(data.placeOrder(orderId, petId, quantity));
    }

    @Given("there is an order with {string}")
    public void thereIsAnOrderWith(String orderId) throws IOException {
        StepDefinition.orderId = orderId;
        reqspec = given().spec(requestSpecification());
    }
}
