package com.tutorialsninja.testsuite;

import com.tutorialsninja.customlisteners.CustomListeners;
import com.tutorialsninja.pages.*;
import com.tutorialsninja.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(CustomListeners.class)
public class AccountRegisterPageTest extends BaseTest {
    HomePage homePage = new HomePage();
    AccountRegisterPage accountRegisterPage = new AccountRegisterPage();
    MyAccountPage accountPage = new MyAccountPage();
    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        accountRegisterPage=new AccountRegisterPage();
        accountPage=new MyAccountPage();

    }
    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        homePage.clickOnMyAccountTab();
        homePage.selectMyAccountOptions("Register");
        Assert.assertEquals(accountRegisterPage.getRegisterAccountText(),
                "Register Account", "Register page not displayed");
    }

    @Test(groups = {"smoke","regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        homePage.clickOnMyAccountTab();
        homePage.selectMyAccountOptions("Register");
        accountRegisterPage.enterFirstName("Harry" + (4));
        accountRegisterPage.enterLastName("Porter" + getRandomString(4));
        accountRegisterPage.enterEmail("harry" + getRandomString(4) + "@gmail.com");
        accountRegisterPage.enterTelephone("07328112233");
        accountRegisterPage.enterPassword("test123");
        accountRegisterPage.enterConfirmPassword("test123");
        accountRegisterPage.selectSubscription("Yes");
        accountRegisterPage.clickOnPrivacyPolicyCheckBox();
        accountRegisterPage.clickOnContinueButton();
        Assert.assertEquals(accountPage.getYourAccountHasBeenCreatedText(), "Your Account Has Been Created!",
                "Account not created");
        accountPage.clickOnContinueButton();
        accountPage.clickOnMyAccount();
        accountPage.clickOnLogoutButton();
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
        //accountPage.clickOnContinueButton();
    }

}
