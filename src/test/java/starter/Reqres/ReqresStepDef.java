package starter.Reqres;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.Matchers.equalTo;

public class ReqresStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //scenario 1
    @Given("Get list user with parameter page {int}")
    public void getListUserWithParameterPage(int page) {

        reqresAPI.getListUsers(page);
    }

    @When("Send get list user request")
    public void sendGetListUserRequest() {

        SerenityRest.when().get(ReqresAPI.GET_LIST_USERS);
    }

    @Then("Status code should be {int} OK")
    public void statusCodeShouldBeOK(int OK) {

        SerenityRest.then().statusCode(OK);
    }

    @And("Response body page should be {int}")
    public void responseBodyPageShouldBe(int page) {

        SerenityRest.then().body(ReqresResponses.PAGE,equalTo(page));
    }

    //Scenario 2
    @Given("Post create user with valid json")
    public void postCreateUserWithValidJson() {
        File json = new File(ReqresAPI.JSON_REQ_BODY+"/CreateUser.json");
        reqresAPI.postCreateNewUser(json);
    }

    @When("Send post create user request")
    public void sendPostCreateUserRequest() {
        SerenityRest.when().post(ReqresAPI.POST_CREATE_NEW_USER);
    }

    @Then("Status code should be {int} Created")
    public void statusCodeShouldBeCreated(int created) {
        SerenityRest.then().statusCode(created);
    }

    @And("Response body should contain name {string} and job {string}")
    public void responseBodyShouldContainNameAndJob(String name, String job) {
        SerenityRest.then()
                    .body(ReqresResponses.NAME,equalTo(name))
                    .body(ReqresResponses.JOB,equalTo(job));
    }

    //scenario 3
    @Given("Put update user with valid json with id {int}")
    public void putUpdateUserWithValidJsonWithId(int id) {
        File json = new File(ReqresAPI.JSON_REQ_BODY+"/UpdateUser.json");
        reqresAPI.putUpdateUser(id,json);
    }

    @When("Send put update user request")
    public void sendPutUpdateUserRequest() {
        SerenityRest.when().put(ReqresAPI.PUT_UPDATE_USER);
    }

    //scenario 4
    @Given("Delete user with id {int}")
    public void deleteUserWithId(int id) {
        reqresAPI.deleteUser(id);
    }

    @When("Send delete user request")
    public void sendDeleteUserRequest() {
        SerenityRest.when().delete(ReqresAPI.DELETE_USER);
    }

    @Then("Status code should be {int} No Content")
    public void statusCodeShouldBeNoContent(int noContent) {
        SerenityRest.then().statusCode(noContent);
    }
}
