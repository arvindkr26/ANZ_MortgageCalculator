package com.anzmortgage.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.anzmortgage.pom.AppIndependent;
import com.anzmortgage.pom.MortgagePage;
import com.anzmortgage.pom.RepaymentsPage;


public class RepaymentsCalculatorTestscript{

	public WebDriver driver;
	
	MortgagePage mortgagepage;
	AppIndependent appInd;
	RepaymentsPage repaymentspage;
	
	@BeforeClass
	public void Precondition()
	{
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://www.anz.co.nz/personal/home-loans-mortgages/mortgage-calculators/");
    }
	
	@AfterClass
	public void PostCondition()
	{
        driver.quit();
    }
	
		
	@Test(enabled=true)
	public void TC01_VerifyRepaymentsPage() throws InterruptedException
	{
		mortgagepage= new MortgagePage(driver);
		repaymentspage=new RepaymentsPage(driver);
		appInd= new AppIndependent(driver);
		mortgagepage.naviateToRepayment();
		appInd.WaitFor(driver, null, "title", "Repayments Calculator | What will my home loan repayments be? | ANZ Store", 60);
		mortgagepage.verifyRepaymentsPage("Repayments Calculator | What will my home loan repayments be? | ANZ Store");

    }
	
	@Test(enabled=false)
	public void TC02_VerifyEmptyLoanAmountValidation()
	{
		mortgagepage= new MortgagePage(driver);
		repaymentspage=new RepaymentsPage(driver);
		appInd= new AppIndependent(driver);
		repaymentspage.clickCalculateBtn();
		System.out.println("Empty Loan amount validation error message displayed is ");
		repaymentspage.verifyFieldValidationMsg("Please enter the loan amount for this calculation");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	@Test(enabled=false)
	public void TC03_VerifyInvalidLoanAmountValidation()
	{
		mortgagepage= new MortgagePage(driver);
		repaymentspage=new RepaymentsPage(driver);
		appInd= new AppIndependent(driver);
		repaymentspage.enterLoanAmount("1000");
		repaymentspage.clickCalculateBtn();
		System.out.println("Invalid Loan amount validation error message displayed is ");
		repaymentspage.verifyFieldValidationMsg("Loan amount must be between $5,000 and $5,000,000");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	@Test(enabled=true)
	public void TC04_CalculateRepayments_Scenario1()
	{
		mortgagepage= new MortgagePage(driver);
		repaymentspage=new RepaymentsPage(driver);
		appInd= new AppIndependent(driver);
		repaymentspage.enterLoanAmount("20000");
		repaymentspage.clickloanLengthdropdown();
		repaymentspage.selectLoanYear("10 years");
		repaymentspage.clickCalculateBtn();
		repaymentspage.clickCalculateBtn();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	@Test(enabled=true)
	public void TC05_VerifyMonthlyRepayments_Scenario1()
	{
		repaymentspage.verifyMonthlyRepayments("1","220","$20,000","$6,393","$26,393");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

	}
	
	@Test(enabled=true)
	public void TC06_createScenario()
	{
		repaymentspage.createScenario();
	}
	
	@Test(enabled=true)
	public void TC07_CalculateRepayments_Scenario2()
	{
		repaymentspage.enterLoanAmount2("60000");
		repaymentspage.clickCalculateBtn2();
    }
	
	@Test(enabled=true)
	public void TC08_VerifyFortnightlyRepayments_Scenario2()
	{
		repaymentspage.repaymentFrequency("Fortnightly");
		repaymentspage.verifyFortnightlyRepayments("2","329","$60,000","$17,050","$77,050");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@Test(enabled=true)
	public void TC09_VerifyScenarioComparionResult()
	{
		repaymentspage.scenarionComparisonResult("$50,657");
	}
	

}
