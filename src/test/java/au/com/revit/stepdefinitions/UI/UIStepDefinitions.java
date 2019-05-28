package au.com.revit.stepdefinitions.UI;

import au.com.revit.core.AssertObject;
import au.com.revit.core.World;
import au.com.revit.pages.UiPage;
import au.com.revit.utilities.ScenarioContext;
import au.com.revit.utilities.WaitUtils;
import cucumber.api.java8.En;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UIStepDefinitions implements En {


    private World world;
    private ScenarioContext m_scenarioContext;

    public UIStepDefinitions(World world, ScenarioContext scenarioContext) {
        this.world = world;
        this.m_scenarioContext = scenarioContext;
        Given("^I navigate to the application$", () -> {
            try {
                navigateToApplication();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Then("^I verify that I navigated to right page$", () -> {
            verifyApplicationIsRight();
        });
        And("^I click on locate us button$", () -> {
            clickLocateButton();
        });
        When("^I enter suburb details \"([^\"]*)\"$", (String suburb) -> {
            enterSuburbDetails(suburb);
        });
        Then("^I should see service centre named \"([^\"]*)\"$", (String serviceCentre) -> {
            verifyServiceCentre(serviceCentre);
        });
        Then("^I close the browser$", () -> {
            try {
                UiPage.getInstance(this.world.getDriver()).closeBrowser();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void navigateToApplication() throws InterruptedException {
        UiPage.getInstance(this.world.getDriver()).navigateToApplicationUrl();
    }

    private void verifyApplicationIsRight() {
        try {
            AssertObject softly = World.getInstance().getSoftAssertionObject();
            softly.assertThat(UiPage.getInstance(this.world.getDriver()).isRightApplication()).as("Navigated to appropriate page").isEqualTo(true);
            softly.assertAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clickLocateButton() {
        try {
            WebElement element = UiPage.getInstance(this.world.getDriver()).getLocateUs();
            element.click();
            WaitUtils.waitABit(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enterSuburbDetails(String suburb) {
        try {
            WebElement searchSuburbInput = UiPage.getInstance(this.world.getDriver()).getSearchSuburbInput();
            WebElement searchSuburbButton = UiPage.getInstance(this.world.getDriver()).getSearchSuburbButton();
            if (searchSuburbInput.isEnabled()) {
                searchSuburbInput.clear();
                searchSuburbInput.sendKeys(suburb);
                searchSuburbInput.sendKeys(Keys.RETURN);
            }
            WaitUtils.waitABit(1000);
            searchSuburbButton.click();
            WaitUtils.waitABit(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void verifyServiceCentre(String serviceCentre) {
        try {
            WaitUtils.waitABit(3000);
            AssertObject softly = World.getInstance().getSoftAssertionObject();
            List<WebElement> serviceCentreLocationsDiv = UiPage.getInstance(this.world.getDriver()).getServiceCentreLocationsDiv();
            WebElement anchorElement = null;
            for (WebElement element : serviceCentreLocationsDiv) {
                if (!element.getTagName().equalsIgnoreCase("a")) {
                    continue;
                }
                if (!element.getText().equalsIgnoreCase(serviceCentre)) {
                    continue;
                }
                anchorElement = element;
                break;

            }
            softly.assertThat(anchorElement == null).as("Service centre location is invalid").isEqualTo(false);
            softly.assertAll();
            WaitUtils.fluentlyWaitUntilElementIsClickable(anchorElement, this.world.getDriver(), 6);
            WaitUtils.waitABit(3000);
            anchorElement.click();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
