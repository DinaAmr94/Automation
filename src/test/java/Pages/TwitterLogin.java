package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TwitterLogin {
	WebDriver driver;
	
	By userName = By.xpath("//input[@name='session[username_or_email]']");
	By Password = By.xpath("//input[@name='session[password]']");
	By LoginBtn = By.xpath("//div[@role='button']");

	public TwitterLogin(WebDriver driver) {
		this.driver = driver;
	}
	
	public By getUserNameObj() {
		return userName;
	}
	
	public void setUserName(String username) {
		driver.findElement(userName).sendKeys(username);
	}
	
	public void setPassword(String password) {
		driver.findElement(Password).sendKeys(password);
	}
	
	public void clickLoginBtn() {
		driver.findElement(LoginBtn).click();
	}
	
	public void loginToTwitter(String username,String password) {
		this.setUserName(username);
		this.setPassword(password);
		this.clickLoginBtn();
	}
}

