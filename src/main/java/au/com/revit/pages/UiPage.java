package au.com.revit.pages;

import au.com.revit.utilities.UrlLocator;
import au.com.revit.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UiPage extends PageFactory {

    public WebDriver m_driver;

    @FindBy(xpath = "//div[@class='locate-us-button']")
    private WebElement locateUs;

    @FindBy(xpath = "//form[@class='locator__search']//input[@id='locatorTextSearch']")
    private WebElement searchSuburbInput;

    @FindBy(xpath = "//form[@class='locator__search']//input[@id='locatorTextSearch']")
    private WebElement searchSuburbButton;

    @FindBy(xpath = "//div[@class='locator__results-list']//*")
    private List<WebElement> serviceCentreLocationsDiv;

    public UiPage(WebDriver driver) {
        this.m_driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static UiPage getInstance(WebDriver driver) {
        return new UiPage(driver);
    }

    public void navigateToApplicationUrl() {
        loadUrlInBrowser();
    }

    public void loadUrlInBrowser() {
        WaitUtils.waitABit(3000);
        m_driver.navigate().to(UrlLocator.getInstance().getApplicationURL());
        WaitUtils.waitABit(3000);
    }

    public boolean isRightApplication() {
        WaitUtils.waitABit(3000);
        if(m_driver.getTitle().equalsIgnoreCase("Home | Service NSW")){
            return true;
        }
        return false;
    }

    public WebElement getLocateUs(){
        return locateUs;
    }

    public WebElement getSearchSuburbInput() {
        return searchSuburbInput;
    }

    public WebElement getSearchSuburbButton() {
        return searchSuburbButton;
    }

    public List<WebElement> getServiceCentreLocationsDiv() {
        return serviceCentreLocationsDiv;
    }

    public void closeBrowser() {
        m_driver.quit();
    }

}
