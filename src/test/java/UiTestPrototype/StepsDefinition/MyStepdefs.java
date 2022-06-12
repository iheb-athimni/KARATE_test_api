package UiTestPrototype.StepsDefinition;

import UiTestPrototype.General.generalFunction;
import UiTestPrototype.Page.LoginPage;
import UiTestPrototype.Page.apitesting;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class MyStepdefs extends generalFunction {
    @Autowired
    private LoginPage loginPage;

    @Autowired
    private apitesting apitesty;

    @Given("^Open the browser$")
    public void browserIsOpen(){
        getTheDriver();
        System.out.println("step 1 : the "+prop.getProperty("navigator")+" browser is open");
    }

    @And("^I open the Authentication Page$")
    public void iOpenTheAuthenticationPage() throws IOException {
        openUrl(prop.getProperty("test_page_url"));
        System.out.println("step 2 : the Authentication page is open ");
    }

    @When("^User enter the username as \"([^\"]*)\"and password as \"([^\"]*)\"$")
    public void userEnterTheUsernameAsAndPasswordAs(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage = new LoginPage();
        loginPage.logIn(prop.getProperty("username"), prop.getProperty("passWord"));
        System.out.println("step 3 : the username and the password settled  ");
    }

    @And("^click on Login$")
    public void clickOnLogin() {
        loginPage.clickLogin();
        System.out.println("step 4 : click on the Login button ");
    }

    @Then("^the User Navigate to the result Page$")
    public void theUserNavigateToTheResultPage() {
        System.out.println("step 5 : check the result page opened ");
    }

    @And("^Close the browser$")
    public void closeTheBrowser() {
        if(driver != null){
            removeDriver();
            System.out.println("step 6 : Closing the browser ");
        }
    }

    @Given("^check the API respence$")
    public void checkTheAPIRespence() throws IOException {
        apitesty = new apitesting();
        apitesty.testApiSecond();
    }
}
