import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    /*WebDriver driver;*/
    public void TakeScreenshoots(WebDriver driver){
        try {
            // Convert WebDriver object to TakesScreenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;

            // Capture the screenshot as a file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
          // String destFilePath = "path/to/save/screenshot_" + timestamp + ".png";
            String destFilePath = "src/test/Screenshot/screenshot_" + timestamp + ".png";
            // Define the destination file path
            File destFile = new File(destFilePath);

            //File destFile = new File("src/test/Screenshot/screenshot.png");

            // Copy the file to the destination path
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot captured and saved at: " + destFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

