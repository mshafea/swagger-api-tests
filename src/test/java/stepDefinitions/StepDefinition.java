package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String id;


    @Given("Add Pet Payload with {string} {string} {string}")
    public void add_pet_payload_with(String id, String name, String status) throws IOException {
        reqspec = given().spec(requestSpecification()).body(data.addPet(id, name, status));
    }

    @When("user calls {string} with {string} https request")
    public void user_calls_with_https_request(String resource, String httpMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqspec.when().post(resourceAPI.getResource());
            id = getJsonPath(response, "id");
                    System.out.println("id :: "+id);
        } else if (httpMethod.equalsIgnoreCase("GET"))
            response = reqspec.when().get(resourceAPI.getResource() + "{id}");
        else if (httpMethod.equalsIgnoreCase("DELETE"))
            response = reqspec.when().delete(resourceAPI.getResource() + "{id}");
    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int code) {
        assertEquals(code, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Given("Delete Pet Payload")
    public void deletePetPayload() throws IOException {
        reqspec = given().spec(requestSpecification()).pathParam("id", id);
    }

    @And("Add Order Payload with {string} {string} {string}")
    public void add_order_payload_with(String orderId, String petId, String quantity) throws IOException {
        reqspec = given().spec(requestSpecification()).body(data.placeOrder(orderId, petId, quantity));
    }

    @Given("Delete Order Payload")
    public void delete_order_payload() throws IOException {
        reqspec = given().spec(requestSpecification().pathParam("id", id));
    }
}
