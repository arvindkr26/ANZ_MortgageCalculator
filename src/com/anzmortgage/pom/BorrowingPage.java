package com.anzmortgage.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BorrowingPage extends AppIndependent {
	
public WebDriver driver;
	
	public BorrowingPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath="//span[@class='primaryHeading']")
	private WebElement headingText;
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//span[@class='field-validation-error']")
	private WebElement householdValidationMsg;
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//div[contains(@class,'radioButtons')]//label")
	private List<WebElement> radiobuttons;	
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//input[@id='Income_AnnualHousehold']")
	private WebElement annualHouseholdAmt;
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//input[@value='Calculate']")
	private WebElement  calculateButton;
	
	@FindBy(xpath="//p[@id='amountAbleToBorrow']")
	private WebElement  borrowingCalculatedResult;
	
	@FindBy(xpath="//label[@id='simpleJointType']")
	private WebElement  jointAccountRadioButton;
	
	@FindBy(xpath="//label[@id='simpleIndividualType']")
	private WebElement  individualAccountRadioButton;
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//input[@id='LoanDetails_Dependants']")
	private WebElement  dependantChildren;
	
	@FindBy(xpath="//div[@id='js-simpleFormWrapper']//input[@id='LoanDetails_Vehicles']")
	private WebElement  vehicles;
	
	@FindBy(xpath="//a[@id='js-affordabilityDiffScenario']")
	private WebElement  tryDiffRepaymentScenarioLink;
	
	
	public void verifyBorrowingHeader(String expectedHeading)
	{
		String actualHeading=headingText.getText();
		Assert.assertEquals(actualHeading, expectedHeading);
    	System.out.println("Borrowing page Title :"+actualHeading);
	}
	
	public void verifyHouseholdValidation(String expectedVal)
	{
		String actualVal=householdValidationMsg.getText();
		Assert.assertEquals(actualVal, expectedVal);
    	System.out.println("Validation message for empty annual household field :"+actualVal);
	}
	
	public void verifyDisplayOfApplicationtypes()
	{
		System.out.println("Application types available are :");
		for(int i=0;i<=radiobuttons.size()-1;i++)
		{
			String rbuttons=radiobuttons.get(i).getText();
			System.out.println(rbuttons);
		}		
	}
	
	public void verifyDefaultCheckedApplicationtype()
	{
		for(int i=0;i<=radiobuttons.size()-1;i++)
		{
			if(radiobuttons.get(i).getAttribute("checked").equals("checked"))
			{
				if(radiobuttons.get(i).getText().equals("Individual"))
				{
					System.out.println("Default Application type selected is :" +radiobuttons.get(i).getText());
					break;
				}
				else
				{
					System.out.println("Default Application type selected is wrong");
				}
				
			}
		}		
	}
	
	public void enterAnnualHouseholdAmount(String amount)
    {
		annualHouseholdAmt.clear();
		annualHouseholdAmt.sendKeys(amount);
    }

	public void clickCalculateBtn()
    {
    	if(calculateButton.isDisplayed())
    	{
        	calculateButton.click();
        	try {
    			Thread.sleep(5000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		} 	
    	}
    	else
    	{
    		System.out.println("Calculate button not displayed");
    	}
    }
	
	public void VerifyborrowingResult(String expectedborrow)
	{
		String actualBorrow=borrowingCalculatedResult.getText();
		Assert.assertEquals(actualBorrow, expectedborrow);
    	System.out.println(actualBorrow);
	}
	
	public void selectJointRadioButton()
	{
		jointAccountRadioButton.click();
	}
	
	public void selectIndividualRadioButton()
	{
		individualAccountRadioButton.click();
	}
	
	public void enterNumberOfDependants(String number)
	{
		dependantChildren.clear();
		dependantChildren.sendKeys(number);
	}
	
	public void enterVehicles(String number)
	{
		vehicles.clear();
		vehicles.sendKeys(number);
	}
	
	public void clickRepaymentLink()
	{
		tryDiffRepaymentScenarioLink.click();
	}
	
	
}
