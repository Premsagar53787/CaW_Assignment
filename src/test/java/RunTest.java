import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class RunTest {
    WebDriver driver;
    @Test
    public void Run() throws InterruptedException{
        StepsToExecute run = new StepsToExecute(driver);
        run.Steps();
    }
}
