package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static utils.ExtentReport.fail;

public class Helper {

    public static String getCurrentTime(String dateFormat) {
        String currentTime = "";
        try {
            currentTime = new SimpleDateFormat(dateFormat).format(new Date());
        } catch (IllegalArgumentException e) {
            Logger.logMessage(e.getMessage());
            fail(e.getMessage());
        }
        return currentTime;
    }
    public static String getCurrentTimeStamp() {
        return getCurrentTime("ddMMyyyyHHmmssSSS");
    }

    public static int getRandomNumberBetweenTwoValues(int lowValue, int highValue) {
        return new Random().nextInt(highValue - lowValue) + lowValue;
    }

    public static String getRandomNumberBetweenTwoValuesAsString(int lowValue, int highValue) {
        return Integer.toString(getRandomNumberBetweenTwoValues(lowValue, highValue));
    }


    public static void openBaseUrl(String baseUrl , WebDriver driver){
        while (true) {
            try {
                // Navigate to the URL that is causing the error
                driver.get(baseUrl);
                // If the page is loaded successfully, break out of the loop
                break;
            } catch (WebDriverException e) {
                // If the error is "net::ERR_NAME_NOT_RESOLVED", refresh the page
                if (e.getMessage().contains("net::ERR_NAME_NOT_RESOLVED")) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    driver.navigate().refresh();
                } else {
                    // If the error is not "net::ERR_NAME_NOT_RESOLVED", throw the exception
                    throw e;
                }
            }
        }

    }

    public static void waitFor(int mSeconds){

        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
