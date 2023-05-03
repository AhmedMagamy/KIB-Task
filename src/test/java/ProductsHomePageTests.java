import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AddProductPage;
import pages.ProductsHomePage;
import utils.Helper;
import utils.TestngListener;


@Listeners(TestngListener.class)
public class ProductsHomePageTests {
    public WebDriver driver;
    ProductsHomePage productsHomePage;
    AddProductPage addProductPage;
    String baseUrl = "https://e-commerce-kib.netlify.app/";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        Helper.openBaseUrl(baseUrl,driver);
        productsHomePage = new ProductsHomePage(driver);
         addProductPage =new AddProductPage(driver);




    }

    @Test(description = "Verify that the home page is displayed correctly with all the necessary elements such as logo,\n" +
            "navigation menu, search bar, and footer.")
    public void tc001VerifyHomePageElementsAreDisplayedSuccessfully() {
        //After the baseurl is opened then we check that all elements are displayed
        Assert.assertTrue(productsHomePage.isLogoDisplayed());
        Assert.assertTrue(productsHomePage.isNavMenuDisplayed());
        Assert.assertTrue(productsHomePage.isSearchBarDisplayed());
        Assert.assertTrue(productsHomePage.isFooterDisplayed());

    }

    @Test(description = "Verify search with invalid data is working correctly")
    public void tc002VerifyThatSearchWithInvalidDataIsWorkingSuccessfully() {
        //test data
        String searchKeyword = "InvalidData";
        //Enter the search keyword
        productsHomePage.fillSearchField(searchKeyword);
        //checking the displayed result
        Assert.assertTrue(productsHomePage.isNoProductsMsgDisplayed());

    }
    @Test(description = "Verify that search with valid data is working correctly")
    public void tc003VerifyThatSearchWithValidDataIsWorkingSuccessfully() {
        //test data
        String searchKeyword = "Desert";
        //Enter the search keyword
        productsHomePage.fillSearchField(searchKeyword);
        //checking the displayed result
        Assert.assertTrue(productsHomePage.isSearchResultMatchingSearchKeyWord(searchKeyword));

    }

    @Test(description = "Verify product name,description and price are displayed successfully for a newly created product ")
    public void tc004VerifyProductList() {
        //test data
        String projPath = System.getProperty("user.dir");
        String imagePath = projPath+"/src/main/resources/product.png";
        String productName = "Testing Product";
        String productDesc = "This product is created for Testing";
        String price = "70";
        //go to add product page
        productsHomePage.goToAddProductsPage();
        //create new product
        addProductPage.fillProductDetails(imagePath,productName,productDesc,price);
        addProductPage.clickCreateProduct();
        //search for the created product
        productsHomePage.fillSearchField(productName);
        //check the Name , price and description are displayed correctly on the card
        Assert.assertTrue(productsHomePage.geProductNameFromTheProductCard().equals(productName));
        Assert.assertTrue(productsHomePage.geProductDescFromTheProductCard().equals(productDesc));
        Assert.assertTrue(productsHomePage.geProductPriceFromTheProductCard().contains(price));
        //clean up step - we delete the created product
        productsHomePage.clickDeleteProduct(productName);

    }


    @Test(description = "Verify that user is able to add a new product successfully ")
    public void tc005VerifyAddingNewProductSuccessfully() {
        //test data
        String projPath = System.getProperty("user.dir");
        String imagePath = projPath+"/src/main/resources/product.png";
        String productName = "Testing Product";
        String productDesc = "This product is created for Testing ";
        String price = "60";
        //go to add product page
        productsHomePage.goToAddProductsPage();
        //create new product
        addProductPage.fillProductDetails(imagePath,productName,productDesc,price);
        addProductPage.clickCreateProduct();
        //search for the created product
        productsHomePage.fillSearchField(productName);
        //check that the displayed product name is matching with the created product
        Assert.assertTrue(productsHomePage.isSearchResultMatchingSearchKeyWord(productName));
        //clean up step - we delete the created product
        productsHomePage.clickDeleteProduct(productName);

    }
    @Test(description = "Verify that user is able to edit a newly created product successfully ")
    public void tc006VerifyEditingNewProductSuccessfully() {
        //test data
        String projPath = System.getProperty("user.dir");
        String imagePath = projPath+"/src/main/resources/product.png";
        String productName = "Testing Product";
        String productDesc = "This product is created for Testing ";
        String price = "60";
        String newEditedName = productName+"edited";
        //go to add product page
        productsHomePage.goToAddProductsPage();
        //create new product
        addProductPage.fillProductDetails(imagePath,productName,productDesc,price);
        addProductPage.clickCreateProduct();
        //search for the created product
        productsHomePage.fillSearchField(productName);
       //click edit product
        productsHomePage.clickEditProduct(productName);
        //fill with new edited name
        addProductPage.editProductName(newEditedName);
        //save the new details
        addProductPage.clickSaveProduct();
        //search for the product by the new name
        productsHomePage.fillSearchField(newEditedName);
        //check the product is displayed with the new name
        Assert.assertTrue(productsHomePage.isSearchResultMatchingSearchKeyWord(newEditedName));
        //clean up step - we delete the created product
        productsHomePage.clickDeleteProduct(productName);
    }


    @AfterMethod
    public void cleanUp() {
       driver.close();

    }

}