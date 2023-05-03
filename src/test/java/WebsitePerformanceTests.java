import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.openqa.selenium.devtools.v106.network.model.ConnectionType;
import org.openqa.selenium.devtools.v106.performance.Performance;
import org.openqa.selenium.devtools.v106.performance.model.Metric;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Listeners(TestngListener.class)
public class WebsitePerformanceTests {
    public WebDriver driver;
    String baseUrl = "https://e-commerce-kib.netlify.app/";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }


    @Test
    public void readAllPerformanceMetricsUsingDevTools() {
        //open  the website under test
        Helper.openBaseUrl(baseUrl, driver);
        // get chrome dev tools
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                20,
                20,
                50,
                Optional.of(ConnectionType.WIFI)
        ));
        //Read all the metrics using Chrome dev tools
        List<Metric> metricList = devTools.send(Performance.getMetrics());
        //Log all the metrics to the html test execution report
        for (Metric m : metricList) {
            Logger.logMessage(m.getName() + " = " + m.getValue());
        }


    }

    @Test
    public void checkPerformanceScoresUsingPageInsights() throws IOException {
        //Navigate to page speed insights
        driver.get("https://pagespeed.web.dev/");
        //Entering the website under test in the URL field
        UiActions.type(driver, By.xpath("//input[@placeholder='Enter a web page URL']"), baseUrl);
        //click analyze
        UiActions.click(driver, By.xpath("//button[normalize-space()='Analyze']"));
        //select desktop
        UiActions.click(driver, By.xpath("//span[contains(text(),'Desktop')]"));
        //wait until all scores are displayed
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@href=\"#performance\"])[4]")));
        //Taking a screenshot of the scores and adding it to the html execution report
        ExtentReport.info(Logger.attachScreenshotToExtentReport(driver));

    }


    @AfterMethod
    public void cleanUp() {
        driver.close();

    }

}
