package com.anzmortgage.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MortgagePage{

	public WebDriver driver;
	 
    @FindBy(xpath="//a[contains(text(),'Repayments calculator')]")
    private WebElement repaymentCalLink;
   
    @FindBy(xpath="//a[text()='Borrowing calculator']")
    private WebElement borrowingCalLink;
   
    public MortgagePage(WebDriver driver)
    {
    	this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    
    public void naviateToRepayment()
    {
    	repaymentCalLink.click();
    }
                
     public void verifyRepaymentsPage(String expectedTitle)
     {
    	 String actualTitle=driver.getTitle();
         if(actualTitle.equals(expectedTitle))
         {
         	System.out.println("Repayment caluculator page displayed successfully");
         }
     }
    
   
    public void naviateToBorrowing()
    {
    	borrowingCalLink.click();             
    }
    
    public void verifyBorrowingPage(String expectedTitle)
    {
    	String actualTitle=driver.getTitle();
        if(actualTitle.equals(expectedTitle))
        {
        	System.out.println("Borrow caluculator page displayed successfully");
        }
    }


}
