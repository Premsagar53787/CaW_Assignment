import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;

public class StepsToExecute {
    //Web driver initialisation.
    WebDriver driver;

    //For capturing screenshot calling the method by creating object
    Screenshot scr = new Screenshot();

    //creating the constructor
    public StepsToExecute(WebDriver driver){
        this.driver = driver;
    }

    public void Steps() throws InterruptedException{

        /*Browser Setup*/
        /* System.setProperty("webdriver.chrome.driver", "chromedriver"); */

        WebDriver driver = new ChromeDriver();

        //1. Navigate to the given URL.

        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        /* To Check whether the control entered to the function*/
        System.out.println("Entered in to the Steps function");

        //2. Click on Table Data button , a new input text box will be displayed:

        WebElement ele = driver.findElement(By.xpath("//*[text()='Table Data']"));
        ele.click();
        System.out.println("Element with text(): " + ele.getText());
        System.out.println("Clicked on table data");

        //3.  Insert the following data in input text box :
        // Find the text box element and insert the JSON data
        /*[{"name" : "Bob", "age" : 20, "gender": "male"}, {"name": "George", "age" : 42, "gender": "male"},
        {"name":"Sara", "age" : 42, "gender": "female"}, {"name": "Conor", "age" : 40, "gender": "male"},
        {"name":"Jennifer", "age" : 42, "gender": "female"}]*/

        WebElement textBox = driver.findElement(By.xpath("//textarea[contains(@id, \"jsondata\")]"));
        textBox.click();
        scr.TakeScreenshoots(driver);
        Thread.sleep(5000);
        textBox.clear();
        System.out.println("Inserting data:");

        String[] jsonDataArray = {
                "{ \"name\": \"Bob\", \"age\": \"20\", \"gender\": \"male\" }" ,
                "{ \"name\": \"George\", \"age\": \"42\", \"gender\": \"male\" }" ,
                "{ \"name\": \"Sara\", \"age\": \"42\", \"gender\": \"female\" }" ,
                "{ \"name\": \"Conor\", \"age\": \"40\", \"gender\": \"male\" }" ,
                "{ \"name\": \"Jennifer\", \"age\": \"42\", \"gender\": \"female\" }"
        };

        // Find the text box element and insert each JSON data
        textBox.click();
        String[] jsonData;
        jsonData = jsonDataArray;
        textBox.sendKeys(Arrays.toString(jsonData));
        System.out.println("Json data " + Arrays.toString(jsonData));

        //4. And click on Refresh Table button. //*[@id="refreshtable"]

        driver.findElement(By.xpath("//*[@id=\"refreshtable\"]")).click();
        Thread.sleep(5000);
        System.out.println("Clicked on refresh table");
        scr.TakeScreenshoots(driver);

        //5. The entered data will be populated in the table.

        //5.b. Now assert the data you have stored with the data that is populated in the UI table. Both data should
        //match.

        String expectedJsonDataArray = "["
                + "{ \"name\": \"Bob\", \"age\": \"20\", \"gender\": \"male\" },"
                + "{ \"name\": \"George\", \"age\": \"42\", \"gender\": \"male\" },"
                + "{ \"name\": \"Sara\", \"age\": \"42\", \"gender\": \"female\" },"
                + "{ \"name\": \"Conor\", \"age\": \"40\", \"gender\": \"male\" },"
                + "{ \"name\": \"Jennifer\", \"age\": \"42\", \"gender\": \"female\" }"
                + "]";

        // Find the text box element and get the inserted JSON data

        String insertedJsonData = textBox.getAttribute("value");
        System.out.println(insertedJsonData);
        try {
            // Assert that the inserted JSON data matches the expected JSON data
            JSONAssert.assertEquals(expectedJsonDataArray , insertedJsonData , false);
            System.out.println("JSON data matches!");
        } catch (AssertionError e) {
            System.err.println("JSON data does not match!");
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
