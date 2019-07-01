package it.org.techtime.atlassian.plugins.usermanagement.seleniumtests.util;

import com.adaptavist.arquillian.atlassian.remote.container.scanner.SpringScannerOne;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import io.github.bonigarcia.wdm.WebDriverManager;
import it.org.techtime.atlassian.plugins.usermanagement.processing.CommandInvokerTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
//import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.techtime.atlassian.plugins.usermanagement.common.test.UserSetupHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GenerateTestData extends Arquillian {

    @ComponentImport
    @Inject
    public UserSetupHelper userSetupHelper;


    @Deployment
    public static JavaArchive deployTests () {
        return ShrinkWrap.create(JavaArchive.class, "tests.jar")
                .addClass(GenerateTestData.class)
                .addPackage(CommandInvokerTest.class.getPackage())
                .addClass(SpringScannerOne.class);
    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

//    @BeforeMethod
//    protected void invokeBrowserAndSetTestEnvironment(){
//        WebDriverManager.chromedriver().setup();
//        driver.set(new ChromeDriver());
//        getDriver().manage().deleteAllCookies();
//        getDriver().manage().window().maximize();
//        getDriver().manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
//        getDriver().manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
//        getDriver().navigate().to("http://localhost:2990/jira/plugins/servlet/techtime-usermanagement/audit/runlogs");
//        login("admin", "admin");
//    }

    protected void login(String username, String password){
        getDriver().findElement(By.id("login-form-username")).sendKeys(username);
        getDriver().findElement(By.id("login-form-password")).sendKeys(password);
        getDriver().findElement(By.id("login-form-submit")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(),5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
    }

    private WebDriver getDriver(){
        return driver.get();
    }
//
//    @AfterMethod
//    protected void closeBrowser( ){
//        getDriver().quit();
//    }

    @Test
    public void createUsers() throws Exception {
        System.out.println("Hello world");
        userSetupHelper.setupUser("abd1");

    }

//    @Test
//    @RunAsClient
//    public void testSomething() throws Exception {
//        List<String> frontEndList = UserInterfaceTable.fetchRowData(getDriver());
//        List<String> backEndList = BackendTable.deactivateUsersResultLogCheckList();
//        int columnCount = 1;
//        for(String  value: frontEndList){
//            boolean valueMatched = backEndList.contains(value);
//            Assert.assertTrue(valueMatched, "DeactivateUsersResultLog test failed: Unexpected data found at frontend. Data is at column: " +columnCount +" and data is: " +"''" +value +"''" +" ========== ");
//            columnCount++;
//        }
//    }

}
