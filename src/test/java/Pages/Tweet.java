package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tweet {
	WebDriver driver;
	By tweetConfirmationMsg;
	By tweetErrorMsg;
	
	By tweetTextbox = By.xpath("//div[@class='notranslate public-DraftEditor-content']");
	By tweetBtn = By.xpath("//div[@data-testid='tweetButtonInline']");	

	By GIFBtn = By.xpath("//div[@aria-label='Add a GIF']");
	By GIF = By.xpath("//img[@src='https://media0.giphy.com/media/NEvPzZ8bd1V4Y/giphy_s.gif']");
	By GIFImage = By.xpath("//img[@alt='Thumbs GIF by Sesame Street']");
	
	public Tweet(WebDriver driver) {
		this.driver = driver;
	}
	
	public By getTweetTextbox() {
		return tweetTextbox;
	}
	
	public By getGIFBtn() {
		return GIFBtn;
	}
	
	public void enterText(String text) {
		driver.findElement(tweetTextbox).click();
		driver.findElement(tweetTextbox).sendKeys(text);
	}
	
	public void clearText() {
		driver.findElement(tweetTextbox).clear();
	}
	
	public void clickTweetBtn(){
		driver.findElement(tweetBtn).click();
	}
	
	public String getTweetConfirmationMsg(){
		
		tweetConfirmationMsg = By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div[1]/span");
		String text = driver.findElement(tweetConfirmationMsg).getText();
		System.out.println("TWEET "+text);
		return text;
	}
	
	public String getTweetErrorMsg(){

		tweetErrorMsg = By.xpath("/html/body/div/div/div/div[1]/div/div[1]/div/div/div/div[1]/span");
		String text = driver.findElement(tweetErrorMsg).getText();
		System.out.println("TWEET error msg "+text);
		return text;
	}
	
	public void clickGIFBtn() {
		driver.findElement(GIFBtn).click();
	}
	
	public void selectGIF() {
		driver.findElement(GIF).click();
		driver.findElement(GIFImage).click();
	}
	
	public String checkGIFButtonDisables() {
		String disabledValue=driver.findElement(GIFBtn).getAttribute("aria-disabled");
	
		System.out.println("GIF Button enabled "+disabledValue);
		return disabledValue;
	}
	
	public String checkTweetButtonDisables() {
		String disabledValue=driver.findElement(tweetBtn).getAttribute("aria-disabled");
	
		System.out.println("tweet Button enabled "+disabledValue);
		return disabledValue;
	}
	
	public void closeDriver() {
		driver.close();
	}
	
}

