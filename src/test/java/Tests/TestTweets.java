package Tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import Pages.TwitterLogin;
import Utils.TestUtils;
import Pages.Tweet;;

public class TestTweets {
	
	WebDriver driver;
	TwitterLogin objLogin;
	Tweet objTweet;
	TestUtils testUtils;

	ExtentReports extent;
	ExtentTest logger; 
	
	@BeforeClass
	public void NavigateToTwitterLoginPage()
	{	
		extent = new ExtentReports("E:\\ITI\\ITI Courses\\Automation\\WorkSpace\\Automation\\TestReport\\Report.html",true);

		extent.addSystemInfo("OS", "Windows");
		extent.addSystemInfo("Platform", "GoogleChrome");
		extent.addSystemInfo("Test", "TweetPage");
		extent.addSystemInfo("Tester", "Dina");
		
		System.setProperty("webdriver.chrome.driver","E:\\Programs\\chromedriver_win32\\chromedriver.exe");

		driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.navigate().to("https://twitter.com/login");
		
		driver.manage().window().maximize();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/login");
	}
	
	@Test(priority=1)
	public void LoginByValidCredentials(Method method) throws IOException 
	{
		logger = extent.startTest("LoginByValidCredentials");
		
		objLogin = new TwitterLogin(driver);
		testUtils= new TestUtils(driver);
		
		String username = "Testing97865354";
		String Password = "vodafone_testing_task";

		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objLogin.getUserNameObj()));

		objLogin.loginToTwitter(username, Password);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/home");
	}
	
	@Test(priority=2)
	public void TweetWithValidLength(Method method) throws IOException 
	{		
		logger = extent.startTest("TweetWithValidLength");

		objTweet = new Tweet(driver);
		testUtils= new TestUtils(driver);
		
		String tweetMsg = "Hello World";
		String confirmationMsg = "Your Tweet was sent.";
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));

		objTweet.enterText(tweetMsg);
		objTweet.clickTweetBtn();
				
		testUtils.takeScreenShot(method.getName());

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.getTweetConfirmationMsg(), confirmationMsg);
		
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException ie){
		}

	}
	
	@Test(priority=3)
	public void TweetWithGIF(Method method) throws IOException
	{
		logger = extent.startTest("TweetWithGIF");

		String confirmationMsg = "Your Tweet was sent.";

		objTweet = new Tweet(driver);
		testUtils= new TestUtils(driver);
		
		objTweet.clickGIFBtn();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objTweet.selectGIF();
		
		objTweet.clickTweetBtn();
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.getTweetConfirmationMsg(), confirmationMsg);
		
		testUtils.takeScreenShot(method.getName());

		try{
			Thread.sleep(7000);
		}
		catch(InterruptedException ie){
		}
	}
	
	@Test(priority=4)
	public void TweetWithValidLengthAndGIF(Method method) throws IOException
	{
		logger = extent.startTest("TweetWithValidLengthAndGIF");
		testUtils= new TestUtils(driver);
		
		String confirmationMsg = "Your Tweet was sent.";
		String tweetMsg = "Tweet with valid length and GIF";

		objTweet = new Tweet(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));

		objTweet.enterText(tweetMsg);

		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getGIFBtn()));

		objTweet.clickGIFBtn();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objTweet.selectGIF();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.checkGIFButtonDisables(), "true");
		
		objTweet.clickTweetBtn();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.getTweetConfirmationMsg(), confirmationMsg);
		
		testUtils.takeScreenShot(method.getName());

	}
	
	@Test(priority=5)
	public void emptyTweet(Method method) throws IOException 
	{		
		logger = extent.startTest("emptyTweet");

		objTweet = new Tweet(driver);
		testUtils= new TestUtils(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.checkTweetButtonDisables(), "true");
		
		testUtils.takeScreenShot(method.getName());
		
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException ie){
		}

	}
	
	@Test(priority=6)
	public void TweetWithInvalidLength(Method method) throws IOException 
	{		
		logger = extent.startTest("TweetWithInvalidLength");
		
		testUtils= new TestUtils(driver);
		objTweet = new Tweet(driver);

		String tweetMsg = "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddfddddddddddddddddddddddddddddddddddddddddddd";
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));

		objTweet.enterText(tweetMsg);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.checkTweetButtonDisables(), "true");
		
		testUtils.takeScreenShot(method.getName());

		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException ie){
		}
	}
	
	@Test(priority=7)
	public void postSameTweetAtRow(Method method) throws IOException 
	{		
		logger = extent.startTest("postSameTweetAtRow");

		objTweet = new Tweet(driver);
		testUtils= new TestUtils(driver);
		
		String tweetMsg = "Same Tweet At a Row";
		String confirmationMsg = "Your Tweet was sent.";
		String errorMsg = "Whoops! You already said that.";

		driver.navigate().refresh();
		
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));
		objTweet.clearText();

		wait.until(ExpectedConditions.visibilityOfElementLocated(objTweet.getTweetTextbox()));
		objTweet.enterText(tweetMsg);
		objTweet.clickTweetBtn();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.getTweetConfirmationMsg(), confirmationMsg);
		
		try{
			Thread.sleep(7000);
		}
		catch(InterruptedException ie){
		}
		
		objTweet.enterText(tweetMsg);
		objTweet.clickTweetBtn();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(objTweet.getTweetConfirmationMsg(), errorMsg);
		
		testUtils.takeScreenShot(method.getName());

	}
		
	@AfterMethod
	public void tearDown(Method method, ITestResult result)
	{
		
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Pass");
			logger.log(LogStatus.PASS, "<a href='" + result.getName() +".png" + "'><span class='lable info'>Download Snapshot</span></a>");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(LogStatus.FAIL, "<a href='" + result.getName() +".png" + "'><span class='lable info'>Download Snapshot</span></a>");
		}
		else
			logger.log(LogStatus.SKIP, "Test Skipped");
	}
	
	@AfterClass
	public void closeDriver() 
	{
		objTweet.closeDriver();
		extent.flush();

	}

}
