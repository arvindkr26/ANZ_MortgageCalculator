package com.anzmortgage.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppIndependent {
	
	public WebDriver driver;
	public WebElement ele;

	public AppIndependent(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }
	
	public void WaitFor(WebDriver driver, By objBy, String strWaitFor, String strText, int timeOut)
	{
		WebDriverWait wait=null;
		try
		{
			wait=new WebDriverWait(driver, timeOut);
			switch(strWaitFor.toLowerCase())
			{
				
				case "text":
					wait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, strText));
					break;
				case "clickable":
					wait.until(ExpectedConditions.elementToBeClickable(objBy));
					break;
				case "invisible":
					wait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
					break;
				case "element":
					wait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
					break;
				case "title":
					wait.until(ExpectedConditions.titleIs(strText));
					break;
				case "alert":
					wait.until(ExpectedConditions.alertIsPresent());
					break;
				default:
					//WriteResult("Error", "Wrong condition was passed for WaitFor method");
			}

		}catch(Exception e)
		{
			e.getMessage();
		}
	}

}
