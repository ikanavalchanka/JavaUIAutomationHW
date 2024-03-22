package homework15.runner;
import homework15.step_definitions.LogInPageStepDefinition;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
        "html:target/cucumber-report/cucumber.html",
        "json:target/cucumber-report/cucumber.json",
        },
        features = {"src/test/java/homework15/features/"},
        glue ={"homework15.step_definitions"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
