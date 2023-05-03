package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Helper;
import utils.Logger;
import utils.UiActions;

import java.util.List;

public class ProductsHomePage {

    WebDriver driver;


    //Selectors that are used to locate the web elements and interact with them
    By logo = By.xpath("//a[@href='/']");
    By navMenu = By.xpath("//nav");
    By searchBar = By.xpath("//input[@placeholder='Search for products ...']");
    By footer = By.xpath("//footer");
    By noProductsText = By.xpath("//p[@class='text-center text-sm']");
    By productsNames = By.xpath("//div[@class=\"sc-kuWgmH brWvPg mt-4 cursor-pointer\"]");
    By addProductBtn = By.xpath("//a[@href='/add']");
    By deleteBtn = By.xpath("//button[2]");
    By productPrice = By.xpath("//div[@class=\"card-price\"]");
    By productDesc = By.xpath("//div[@class=\"sc-duSInm jVMiwd text-xs\"]");



    public ProductsHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoDisplayed(){
        UiActions.locateElement(driver,logo);
        return driver.findElement(logo).isDisplayed();
    }
    public boolean isNavMenuDisplayed(){
        UiActions.locateElement(driver,navMenu);
        return driver.findElement(navMenu).isDisplayed();
    }
    public boolean isSearchBarDisplayed(){
        UiActions.locateElement(driver,searchBar);
        return driver.findElement(searchBar).isDisplayed();
    }

    public boolean isFooterDisplayed(){
        //locate element is wrapper method used to scroll to the element and wait until it's displayed before interacting
        UiActions.locateElement(driver,footer);
        return driver.findElement(footer).isDisplayed();
    }

    public void fillSearchField(String searchKeyWord){
        UiActions.type(driver,searchBar,searchKeyWord);
        Helper.waitFor(3000);
    }
    public boolean isNoProductsMsgDisplayed(){
        UiActions.locateElement(driver,noProductsText);
        return driver.findElement(noProductsText).isDisplayed();
    }
    public boolean isSearchResultMatchingSearchKeyWord(String searchKeyword){
        boolean isTextExists = true ;
        UiActions.locateElement(driver,productsNames);
        List <WebElement> products = driver.findElements(productsNames);
        for (WebElement product : products){
            Logger.logMessage(product.getText());
            if (!product.getText().contains(searchKeyword)){
                isTextExists = false;
                break;
            }
        }
            return isTextExists;
        }

    public void goToAddProductsPage(){
    UiActions.click(driver,addProductBtn);
    }

    public void clickDeleteProduct(String productName){
        UiActions.click(driver,deleteBtn);
    }

    public void clickEditProduct(String productName){
        String pName = productName.trim().replaceAll("\\s+", "-").toLowerCase();
        UiActions.click(driver,By.xpath("//a[@href='/edit/"+pName+"']"));
    }

    public String geProductNameFromTheProductCard(){
        UiActions.locateElement(driver,productsNames);
        String name = driver.findElement(productsNames).getText();
        Logger.logMessage(name);
        return name;
    }
    public String geProductPriceFromTheProductCard(){
        UiActions.locateElement(driver,productPrice);
         String price = driver.findElement(productPrice).getText();
        Logger.logMessage(price);
        return price;
    }
    public String geProductDescFromTheProductCard(){
        UiActions.locateElement(driver,productDesc);
         String desc =driver.findElement(productDesc).getText();
        Logger.logMessage(desc);
        return desc;
    }

}
