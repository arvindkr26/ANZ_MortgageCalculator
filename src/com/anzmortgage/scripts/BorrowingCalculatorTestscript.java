package com.anzmortgage.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.anzmortgage.pom.AppIndependent;
import com.anzmortgage.pom.BorrowingPage;
import com.anzmortgage.pom.MortgagePage;
import com.anzmortgage.pom.RepaymentsPage;


public class BorrowingCalculatorTestscript{
	public WebDriver driver;
	
	MortgagePage mortgagepage;
	AppIndependent appInd;
	RepaymentsPage repaymentspage;
	BorrowingPage borrowingpage;
	
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
	public void TC01_VerifyBorrowingPage()
	{
		mortgagepage= new MortgagePage(driver);
		appInd= new AppIndependent(driver);
		borrowingpage =new BorrowingPage(driver);
		mortgagepage.naviateToBorrowing();
		appInd.WaitFor(driver, null, "title", "Borrowing Calculator | How much can I borrow? | ANZ Store", 60);
		mortgagepage.verifyBorrowingPage("Borrowing Calculator | How much can I borrow? | ANZ Store");
		borrowingpage.verifyBorrowingHeader("How much can I borrow?");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

    }
	
	@Test(enabled=true)
	public void TC02_VerifyDisplayofApplicationTypes()
	{
		borrowingpage.verifyDisplayOfApplicationtypes();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	@Test(enabled=false)
	public void TC03_VerifyDefaultApplicationType()
	{
		borrowingpage.verifyDefaultCheckedApplicationtype();
    }
	
	@Test(enabled=false)
	public void TC04_VerifyEmptyAnnualHOuseholdValidation()
	{
		borrowingpage.verifyHouseholdValidation("Please enter a value between $0 and $999,999");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

    }
	
	/* Test case for Borrowing calculator
	 * Application type-Individual
	 * Annual income- $50000
	 * Dependant children- 0
	 * Vehicles-0
	 */
	@Test(enabled=true)
	public void TC05_BorrowingCalculation1()
	{
		borrowingpage =new BorrowingPage(driver);
		borrowingpage.enterAnnualHouseholdAmount("50000");
		borrowingpage.clickCalculateBtn();
		System.out.println("Estimated Amount You can borrow for Individual Account with 0 Dependant child and 0 Vehicle having $50000 Annual household :");
		borrowingpage.VerifyborrowingResult("$312,000");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	/* Test case for Borrowing calculator
	 * Application type-Joint
	 * Annual income- $100000
	 * Dependant children- 2
	 * Vehicles-1
	 */
	@Test(enabled=true)
	public void TC06_BorrowingCalculation2()
	{
		borrowingpage.selectJointRadioButton();
		borrowingpage.enterAnnualHouseholdAmount("100000");
		borrowingpage.enterNumberOfDependants("2");
		borrowingpage.enterVehicles("1");
		borrowingpage.clickCalculateBtn();
		System.out.println("Estimated Amount You can borrow for Joint Account with 2 Dependant child aand 1 Vehicle having $100000 Annual household :");
		borrowingpage.VerifyborrowingResult("$498,000");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
	/* Test case for trying different repayment scenariof from Borrowing page
	 * Application type-Individual
	 * Annual income- $200000
	 * Dependant children- 4
	 * Vehicles-0
	 * Navigate to Repayments page
	 * Calculate the repayment scenario
	 */
	@Test(enabled=true)
	public void TC07_TryingRepaymentScenarioFromBorrowingPage()
	{
		repaymentspage=new RepaymentsPage(driver);
		borrowingpage.selectIndividualRadioButton();
		borrowingpage.enterAnnualHouseholdAmount("200000");
		borrowingpage.enterNumberOfDependants("4");
		borrowingpage.enterVehicles("0");
		borrowingpage.clickCalculateBtn();
		System.out.println("Estimated Amount You can borrow for Individual Account with 4 Dependant child aand 0 Vehicle having $200000 Annual household :");
		borrowingpage.VerifyborrowingResult("$1,383,000");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		borrowingpage.clickRepaymentLink();
		mortgagepage.verifyRepaymentsPage("Repayments Calculator | What will my home loan repayments be? | ANZ Store");
		String loanAmount=repaymentspage.getLoanAmount();
		if(loanAmount.contains("1,383,000"))
		{
			System.out.println("Borrowing result and Repayment loan amount are equal");
			repaymentspage.clickCalculateBtn();
			repaymentspage.verifyMonthlyRepayments("1", "8,106", "$1,383,000", "$1,535,156", "$2,918,156");
		}
		else
		{
			System.out.println("Borrowing result and Repayment loan amount are not equal");

		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }
	
}
