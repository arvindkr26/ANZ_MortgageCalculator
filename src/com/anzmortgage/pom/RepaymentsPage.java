package com.anzmortgage.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RepaymentsPage extends AppIndependent{
	public WebDriver driver;
	
	public RepaymentsPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[@id='Scenario1']//input[@id='LoanAmount']")
    private WebElement loanAmountField;
   
    @FindBy(xpath="//div[@id='Scenario1']//div[@class='selector rates']")
    private WebElement anzInterestRatesLink;
   
    @FindBy(xpath="//div[@id='Scenario1']//select[@id='LoanLength']")
    private WebElement loanLength;
    
    @FindBy(xpath="//div[@id='Scenario1']//input[@value='Calculate']")
    private WebElement calculateButton;
    
    @FindBy(xpath="//div[@id='Scenario1']//legend[contains(@class,'scenarioLegend')]")
    private WebElement scenarioCount;
    
    @FindBy(xpath="//p[contains(@class,'js-repaymentCalcResult js-monthly monthly-pay xxxlarge repayment-calc-result selected')]")
    private WebElement miminumRepaymentMonthly;
    
    @FindBy(xpath="//div[@id='Scenario1']//p[contains(@class,'js-totalLoanCalc total-loan-calc')]")
    private WebElement loanAmountMonthly;

    @FindBy(xpath="//p[contains(@class,'js-totalInterestCalc js-monthly monthly-pay total-interest-calc selected')]")
    private WebElement totalInterestMonthly;

    @FindBy(xpath="//p[contains(@class,'js-totalCostCalc js-monthly monthly-pay total-cost-calc selected')]")
    private WebElement totalCostMonthly;

    @FindBy(xpath="//span[contains(@class,'add createScenario')]")
    private WebElement createScenariolink;
  
    @FindBy(xpath="//div[@id='Scenario2']//input[@id='LoanAmount']")
    private WebElement loanAmountField2;

    @FindBy(xpath="//div[@id='Scenario2']//input[@value='Calculate']")
    private WebElement calculateButton2;
    
    @FindBy(xpath="//div[@id='Scenario2']//select[@id='RepaymentFrequency']")
    private WebElement repaymentFrequency;
   
    @FindBy(xpath="//div[@id='Scenario2']//legend[contains(@class,'scenarioLegend')]")
    private WebElement scenarioCount2;
    
    @FindBy(xpath="//div[@id='Scenario2']//p[contains(@class,'js-repaymentCalcResult js-fortnightly fortnightly-pay xxxlarge repayment-calc-result selected')]")
    private WebElement miminumRepaymentFortnightly;
    
    @FindBy(xpath="//div[@id='Scenario2']//p[contains(@class,'js-totalLoanCalc total-loan-calc')]")
    private WebElement loanAmountFortnightly;

    @FindBy(xpath="//div[@id='Scenario2']//p[contains(@class,'js-totalInterestCalc js-fortnightly fortnightly-pay total-interest-calc')]")
    private WebElement totalInterestFortnightly;

    @FindBy(xpath="//div[@id='Scenario2']//p[contains(@class,'js-totalCostCalc js-fortnightly fortnightly-pay total-cost-calc')]")
    private WebElement totalCostFortnightly;

    @FindBy(xpath="//span[@class='field-validation-error']//span[@for='LoanAmount']")
    private WebElement loanAmountValidationMsg;
  
    @FindBy(xpath="//div[@id='Scenario1']//div[@class='scenarioComparison']//span[@class='dollar']")
    private WebElement scenarioComparison;
    
    public void enterLoanAmount(String amount)
    {
    	loanAmountField.clear();
    	loanAmountField.sendKeys(amount);
    }
    
    public String getLoanAmount()
    {
    	String loanAmount=loanAmountField.getAttribute("value");
    	return loanAmount;
    }
    public void clickAnzInterestRateLink()
    {
    	if(anzInterestRatesLink.isDisplayed())
    	{
    	anzInterestRatesLink.click();
    	}
    	else
    	{
    		System.out.println("Interest link not displayed");
    	}
    }
    
    public void selectInterestRate(String interest)
    {
    	String interestRatexpath="//div[@id='Scenario1']//ul[@class='dropdownvalues']/li/a[contains(text(),'"+interest+"')]";
    	//WebElement rate=driver.findElement(By.xpath(interestRatexpath));
    	WebElement hover=driver.findElement(By.xpath("//div[@id='Scenario1']//div[@class='dropdownTop']"));
    	Actions action=new Actions(driver);
    	action.moveToElement(hover).perform();
    	   	
    	driver.findElement(By.xpath(interestRatexpath)).click();
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void clickloanLengthdropdown()
    {
    	if(loanLength.isDisplayed())
    	{
        	loanLength.click();
    	}
    	else
    	{
    		System.out.println("Loan dropdown not displayed");
    	}
    }
    
    public void selectLoanYear(String year)
    {
    	Select select=new Select(loanLength);
    	select.selectByVisibleText(year);  
    }
    
    public void verifyFieldValidationMsg(String expectedVal)
    {
    	String actualVal=loanAmountValidationMsg.getText();
    	Assert.assertEquals(actualVal, expectedVal);
    	System.out.println(actualVal);
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
  
    public void verifyMonthlyRepayments(String expectedScenarioCount, String expectedMinRep, String expectedLoanAmt, String expectedTotalInt, String expectedTotalCost )
    {
    	String actualscenarioCount=scenarioCount.getText().split("\\s+")[1];
    	Assert.assertEquals(actualscenarioCount, expectedScenarioCount);
    	System.out.println("Scenario :"+actualscenarioCount);
    	
    	String actualMinRep= miminumRepaymentMonthly.getText().split("\\s+")[1];
    	Assert.assertEquals(actualMinRep, expectedMinRep);
    	System.out.println("Your Minimum Repaymets :"+actualMinRep);
 
    	String actualLoanAmt= loanAmountMonthly.getText();
    	Assert.assertEquals(actualLoanAmt, expectedLoanAmt);
    	System.out.println("Loan amount :"+actualLoanAmt);
    	
    	String actualTotalInt= totalInterestMonthly.getText();
    	Assert.assertEquals(actualTotalInt, expectedTotalInt);
    	System.out.println("Total Interest :"+actualTotalInt);
    	
    	String actualTotalCost= totalCostMonthly.getText();
    	Assert.assertEquals(actualTotalCost, expectedTotalCost);
    	System.out.println("Your total cost :"+actualTotalCost);  
    	System.out.println("++++++++++++++++++++++++++++++++++");
    }
    
    public void createScenario()
    {
    	createScenariolink.click();
    }
    
    public void enterLoanAmount2(String amount)
    {
    	loanAmountField2.clear();
    	loanAmountField2.sendKeys(amount);
    }
    
    
    public void clickCalculateBtn2()
    {
    	if(calculateButton2.isDisplayed())
    	{
        	calculateButton2.click();
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
    
    public void repaymentFrequency(String frequency)
    {
    	repaymentFrequency.click();
    	Select select=new Select(repaymentFrequency);
    	select.selectByVisibleText(frequency);
    }
    
    public void verifyFortnightlyRepayments(String expectedScenarioCount2, String expectedMinRep2, String expectedLoanAmt2, String expectedTotalInt2, String expectedTotalCost2 )
    {
    	String actualscenarioCount2=scenarioCount2.getText().split("\\s+")[1];
    	Assert.assertEquals(actualscenarioCount2, expectedScenarioCount2);
    	System.out.println("Scenario :"+actualscenarioCount2);
    	
    	String actualMinRep2= miminumRepaymentFortnightly.getText().split("\\s+")[1];
    	Assert.assertEquals(actualMinRep2, expectedMinRep2);
    	System.out.println("Your Minimum Repaymets :"+actualMinRep2);
 
    	String actualLoanAmt2= loanAmountFortnightly.getText();
    	Assert.assertEquals(actualLoanAmt2, expectedLoanAmt2);
    	System.out.println("Loan amount :"+actualLoanAmt2);
    	
    	String actualTotalInt2= totalInterestFortnightly.getText();
    	Assert.assertEquals(actualTotalInt2, expectedTotalInt2);
    	System.out.println("Total Interest :"+actualTotalInt2);
    	
    	String actualTotalCost2= totalCostFortnightly.getText();
    	Assert.assertEquals(actualTotalCost2, expectedTotalCost2);
    	System.out.println("Your total cost :"+actualTotalCost2); 
    	System.out.println("++++++++++++++++++++++++++++++++++");

    }
    
    public void scenarionComparisonResult(String expectedRes)
    {
    	String actualRes=scenarioComparison.getText().split("\\s+")[0];
    	Assert.assertEquals(actualRes, expectedRes);
    	System.out.println("Scenario Comparion result-> You can save a total of "+actualRes);
    }
 
}
