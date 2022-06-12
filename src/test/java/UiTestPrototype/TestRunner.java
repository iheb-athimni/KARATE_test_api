package UiTestPrototype;

import com.beust.jcommander.Parameters;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@Parameters(commandNames = {"Browser"})
@CucumberOptions(features = {"src/test/resources/Features"},glue = "UiTestPrototype/StepsDefinition",
        monochrome = true,
        plugin = {"json:target/generate_Json/cucumber.json"}
        )
public class TestRunner {




}
