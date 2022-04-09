package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePet")
    public void beforeDeletePetScenario() throws IOException {
        StepDefinition sd = new StepDefinition();
        if (StepDefinition.id == null) {
            sd.add_pet_payload_with("1234", "TestCat", "AvailableForTest2");
            sd.user_calls_with_https_request("petAPI", "POST");
        }
    }

    @Before("@DeleteOrder")
    public void beforeDeleteOrderScenario() throws IOException {
        StepDefinition sd = new StepDefinition();
        if (StepDefinition.id == null) {
            sd.add_order_payload_with("15", "9988", "3");
            sd.user_calls_with_https_request("storeAPI", "POST");
        }
    }
}
