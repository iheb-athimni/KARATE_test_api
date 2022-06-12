package UiTestPrototype.StepsDefinition;

import UiTestPrototype.General.generalFunction;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.apache.commons.mail.EmailException;
import org.junit.AfterClass;
import org.junit.jupiter.api.TestInstance;
import UiTestPrototype.Mailing.ExecuteTasks;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Hooks extends generalFunction {

    @Before
    public void Initialisation() throws IOException {
        propertySet();
    }

    @After
    public void TearDown() throws EmailException {
        if(driver != null){
            removeDriver();
            System.out.println("Closing the browser ");
        }
//        sendingEmail();
    }

    @AfterClass
    public void afterAll() throws IOException {
        System.out.println("====================== Email started ======================");
        ExecuteTasks.executeSendTasks();
        System.out.println("====================== Email sent ======================");
    }
}
