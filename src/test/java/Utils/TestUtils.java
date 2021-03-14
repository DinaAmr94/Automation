package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {
	
	WebDriver driver;
	
	public TestUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public void takeScreenShot(String name) throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File("E:\\ITI\\ITI Courses\\Automation\\WorkSpace\\Automation\\TestReport\\"+name+".png"));
	}
}
