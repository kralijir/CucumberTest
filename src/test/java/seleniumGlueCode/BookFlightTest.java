package seleniumGlueCode;

import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class BookFlightTest {
    private static WebDriver driver;


    @Given("^user is on NewTour homepage$")
    public void user_is_on_homepage() throws Throwable {
        String webAddress ="http://newtours.demoaut.com/";
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", projectPath+"/webdriver/geckodriver 2");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(webAddress);
        Assert.assertTrue(driver.getCurrentUrl().contains(webAddress));
    }

    @When("^user navigates to REGISTER Page$")
    public void user_navigates_to_Register_Page() throws Throwable {
        driver.findElement(By.linkText("REGISTER")).click();
    }

    @Then("^user register successfully$")
    public void user_register_successfully() throws Throwable {
        driver.findElement(By.name("register")).click();
        Boolean isPresent = false;
        int numberOfTries = 1;
        do {
            isPresent = driver.findElements(By.xpath("//font[contains(text(), 'Thank you for registering')]")).size() > 0;
            Thread.sleep(3);
            numberOfTries++;
        }while (!isPresent && numberOfTries<10);
        Assert.assertTrue(isPresent);
    }

    @When("^user navigates to Flights$")
    public void user_navigates_to_Flights() throws Throwable {
        driver.findElement(By.linkText("Flights")).click();
    }

    @Then("^user enters flight details from \"([^\"]*)\" to \"([^\"]*)\" for date \"([^\"]*)\" / \"([^\"]*)\" to \"([^\"]*)\" / \"([^\"]*)\"$")
    public void user_enter_flight_details(String fromCity, String toCity, int fromDay, int fromMonth, int toDay, int toMonth) throws Throwable {
        driver.findElement(By.xpath("//select[@name='fromMonth']/option[@value='"+fromMonth+"']")).click();
        driver.findElement(By.xpath("//select[@name='fromDay']/option[@value='"+fromDay+"']")).click();

        driver.findElement(By.xpath("//select[@name='toMonth']/option[@value='"+toMonth+"']")).click();
        driver.findElement(By.xpath("//select[@name='toDay']/option[@value='"+toDay+"']")).click();

        driver.findElement(By.xpath("//select[@name='fromPort']/option[@value='"+fromCity+"']")).click();

        driver.findElement(By.xpath("//select[@name='toPort']/option[@value='"+toCity+"']")).click();
    }

    @Then("^user search for the flight$")
    public void user_search_for_the_flight() throws Throwable {
        driver.findElement(By.name("findFlights")).click();
    }

    @When("^user reserve flights$")
    public void user_reserve_flights() throws Throwable {
        driver.findElement(By.name("reserveFlights")).click();
    }

    @When("^user buy flights$")
    public void user_buy_flights() throws Throwable {
        driver.findElement(By.name("buyFlights")).click();
    }

    @Then("^success message is displayed$")
    public void success_message_is_displayed() throws Throwable {
        Boolean isPresent = driver.findElements(By.xpath("//font[contains(text(), 'has been booked!')]")).size() > 0;
        Assert.assertTrue(isPresent);
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}