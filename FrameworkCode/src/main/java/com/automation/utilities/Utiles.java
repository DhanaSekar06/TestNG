package com.automation.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.setup.ProjectSetup;

public class Utiles extends ProjectSetup {
	static WebDriverWait wait;
	public static final int explicitywaitTimeInSec = 180;
	public static final int implicityWaitTimeInSec = 120;

	// stale elements handling
	public static void staleElementHandling(WebElement Element) {
		boolean breakIt = true;
		while (true) {
			breakIt = true;
			try {
				wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
				wait.until(ExpectedConditions.visibilityOf(Element));

			} catch (Exception e) {
				if (e.getMessage().contains("element is not attached")) {
					breakIt = false;
				}
			}
			if (breakIt) {
				break;
			}

		}
	}

	// Implicit Wait
	public static void setImplicitWaitPeriod(int period) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(period));
	}

	// Explicit Wait
	public static void waitForElementToBeClickable(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeVisible(WebElement element) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
		List<WebElement> elements = new ArrayList<WebElement>();
		elements.add(element);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static boolean waitForElementToBeVisibleAndDisplayed(WebElement element) {
		boolean value = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
				value = true;
			}
		} catch (org.openqa.selenium.TimeoutException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		return value;
	}

	public static void waitForTextToBePresentForElement(WebElement Ele, final String text) {
		ExpectedCondition<Boolean> pageAjaxCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return Ele.getText().equalsIgnoreCase(text);
			}
		};
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
		wait.until(pageAjaxCondition);
	}

	// Thread Sleep
	public static void wait(int MilliSec) {
		try {
			Thread.sleep(MilliSec);
		} catch (InterruptedException e) {
		}
	}

	// Check the elements present on page or not
	public static boolean isTextPresent(WebDriver driver, String text) {
		WebElement tempText = driver.findElement(By.tagName("body"));
		if (tempText.getText().contains(text)) {
			return true;
		} else {
			return false;
		}

	}

	// USE WHEN TO CHECK WHETHER CHECKBOX OR RADIO BUTTON IS SELECTED OR NOT
	public static Boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	// TO CHECK WHETHER AN OPTION IS ENABLED OR NOT
	public static Boolean isEnabled(WebElement element) {
		// return element.isEnabled();
		try {
			element.isEnabled();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static Boolean isElementPresentInList(List<WebElement> elements, String text) {
		Boolean present = false;

		for (WebElement obj : elements) {
			if (obj.getText().equalsIgnoreCase(text)) {
				present = true;
				break;
			}
		}
		return present;
	}

	// alerts
	public static Boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public static String getAlertText() {
		String alertText = driver.switchTo().alert().getText();
		return alertText;
	}

	public static void switchToFrame(WebElement frame) {
		wait(2000);
		driver.switchTo().frame(frame);
		wait(2000);
	}

	public static void switchToFrameByIndex(int index) {
		wait(2000);
		driver.switchTo().frame(index);
		wait(2000);
	}

	public static void switchBackFromFrame() {
		wait(2000);
		driver.switchTo().defaultContent();
		wait(2000);
	}

	public static void waitForAlert() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void alertConfirm() {
		waitForAlert();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		wait(2000);
	}

	public static void alertDismiss() {
		waitForAlert();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		wait(2000);
	}

	public static String alertGetText() {
		waitForAlert();
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	// Wait till page load
	public static void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		wait = new WebDriverWait(driver, Duration.ofSeconds(explicitywaitTimeInSec));
		wait.until(pageLoadCondition);
	}

	// Key Press
	public static void keyPress(Keys key) {
		Actions action = new Actions(driver);

		action.keyDown(key);
		action.keyUp(key);
	}

	// ScrollIntoView
	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// scrollToElement
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
		} catch (WebDriverException e) {
		}
	}

	// For Slider
	public static void slider(String loc, WebDriver driver, int num) {
		try {
			Thread.sleep(2000);
			WebElement slider = driver.findElement(By.xpath(loc));
			int width = slider.getSize().getWidth();
			Actions move = new Actions(driver);
			move.dragAndDropBy(slider, (width - num), 0).click();
			move.build().perform();
		} catch (Exception e) {
		}
	}

	// Click Element by javascript
	public static boolean elementClick(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int attempts = 0;
		try {
			js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
		} catch (WebDriverException e) {
		}

		while (attempts < 10) {
			try {
				element.click();
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
			attempts++;
		}
		return false;
	}

	//
	//
	//
	//
	public static String getPageURL() {
		return driver.getCurrentUrl();
	}

	public static void refreshPage() {
		driver.navigate().refresh();
		waitForPageLoad();
	}

	public static void navigateBackPage() {
		wait(1000);
		driver.navigate().back();

		waitForPageLoad();
		wait(1000);
	}

	public static void checkCheckBoxByJavaScript(WebElement element) {
		if (element.isSelected()) {
			System.out.println("checkbox is already checked");
		} else {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			js.executeScript("arguments[0].click();", element);
		}

	}

	public static void checkCheckBox(WebElement element) {
		if (element.isSelected()) {
			System.out.println("checkbox is already checked");
		} else {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			element.click();
		}
	}

	public static void unCheckCheckBox(WebElement element) {
		if (!element.isSelected()) {
			System.out.println("checkbox is unalready checked");
		} else {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			element.click();
		}
	}

	public static List<String> getTextFromElements(List<WebElement> elements) {

		List<String> texts = new ArrayList<String>();
		for (WebElement element : elements) {
			waitForElementToBeVisible(element);
			String val = element.getText().trim();
			if (!val.isEmpty()) {
				texts.add(val);
			}
		}

		return texts;
	}

	public static List<String> getDropDownOptionsInListOfString(WebElement element) {
		List<String> options = new ArrayList<String>();
		Select dpDown = new Select(element);
		for (WebElement elmnt : dpDown.getOptions()) {
			options.add(elmnt.getText().trim());
		}
		return options;
	}

	public static void clickOnElementWithScrollByJavaScript(WebElement element) {
		waitForElementToBeClickable(element);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
		} catch (WebDriverException e) {
		}

		element.click();
	}

	public void click(WebElement Element) {
		waitForElementToBeVisible(Element);
		Element.click();
	}

	public static void clickOnElementByJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void moveToElement(WebElement element) {

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.build().perform();

	}

	public static void scrollToViewElementUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void typeInFieldUsingSelenium(String value, WebElement element) {
		element.sendKeys(value);
	}

	public static void clearTextBox(WebElement element) {
		element.clear();
	}

	public static void customizedDropDownInputChkBx(List<WebElement> elements, String valueToSelect) {
		for (WebElement obj : elements) {
			if (obj.getText().equals(valueToSelect)) {
				checkCheckBoxByJavaScript(obj);
			}

		}
	}

	public void type(WebElement ele, String text) {
		waitForElementToBeVisible(ele);
		ele.sendKeys(text);
	}

	public static void typeInElementAndEnter(WebElement element, String value) {
		element.sendKeys(value);
		element.sendKeys(Keys.ENTER);
	}

	public static String getElementPositionInList(List<WebElement> elements, String value) {
		int position = 0;
		for (WebElement obj : elements) {
			if (obj.getText().equalsIgnoreCase(value)) {
				return Integer.toString(position);
			}
			position++;
		}
		return null;
	}

	public static String getSelectedDropDownOption(WebElement element) {
		Select dpDown = new Select(element);
		return dpDown.getFirstSelectedOption().getText();
	}

	public static void clearAndTypeInElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value.trim());
	}

	public static String getTextFromElement(WebElement element) {
		waitForElementToBeVisible(element);
		return element.getText().trim();
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static int getElementSize(List<WebElement> elements) {
		return elements.size();
	}

	public static void loadPage(String url) {
		driver.get(url);
	}

	public static List<String> getTextFromElementsAttribute(List<WebElement> elements, String attribute) {

		List<String> text = new ArrayList<String>();
		for (WebElement element : elements) {
			text.add(element.getAttribute(attribute).trim());
		}

		return text;
	}

	public static List<String> getTextFromElementsWithoutVisibilityWait(List<WebElement> elements) {

		List<String> texts = new ArrayList<String>();
		for (WebElement element : elements) {
			String val = element.getText().trim();
			if (!val.isEmpty()) {
				texts.add(val);
			}

		}

		return texts;
	}

	public static void loadPage_NewTab(String url) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.open('" + url + "','_blank');");
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	// Mouse Hover
	public static void mouseHoverActionClick(WebElement hoverElement, WebElement actionElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverElement).click().perform();
		wait(2000);
		actionElement.click();

	}

	public static void mouseHoverAction(WebElement hoverElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverElement).perform();
		wait(1000);

	}

	public static void hoverActionClick(WebElement hoverElement, WebElement actionElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverElement);
		wait(2000);
		actions.click(actionElement).build().perform();
		wait(2000);

	}

	public static void dragScrollBarToViewElementByActionClass(WebElement dragger, WebElement dragToElement) {
		Actions builder = new Actions(driver);
		builder.clickAndHold(dragger).dragAndDrop(dragger, dragToElement).build().perform();
		builder.release(dragger).build().perform();

	}

	// window Handle
	public static void controlon_nth_Window(int windowNum) {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(windowNum));
	}

	public static List<String> getSelectedDropDownOptions(WebElement element) {

		List<WebElement> elements = new ArrayList<WebElement>();
		List<String> selectedOptions = new ArrayList<String>();
		Select dpDown = new Select(element);
		elements = dpDown.getAllSelectedOptions();
		selectedOptions = getTextFromElements(elements);

		return selectedOptions;
	}

	public static List<String> getDropDownOptions(WebElement element) {
		List<WebElement> elements = new ArrayList<WebElement>();
		Select dpDown = new Select(element);
		elements = dpDown.getOptions();
		return getTextFromElements(elements);
	}

	// deselect drop down values
	public static void dropDownSelectionReset(WebElement element) {
		Select typeDpDown = new Select(element);
		typeDpDown.deselectAll();
	}

	// Select DropDown values by index
	public static void dropDownSelectionByIndex(WebElement element, int index) {
		Select typeDpDown = new Select(element);

		typeDpDown.selectByIndex(index);
	}

	// Select values from Drop down by Text
	public static void selectDropDownByContainsText(WebElement element, String text) {
		Select typeDpDown = new Select(element);
		String sValue = "";

		if (!text.isEmpty()) {
			List<WebElement> oSize = typeDpDown.getOptions();
			int iListSize = oSize.size();
			for (int i = 0; i < iListSize; i++) {
				// Storing the value of the option
				sValue = typeDpDown.getOptions().get(i).getText().trim();
				if (sValue.contains(text.trim())) {
					typeDpDown.selectByIndex(i);
					break;
				}
			}
		}

	}

	// Select values from Drop down by Text
	public static void dropDownSelectionByEqualsIgnoreCaseText(WebElement element, String text) throws Exception {
		Select typeDpDown = new Select(element);
		String sValue = "";
		if (!text.isEmpty()) {
			List<WebElement> oSize = typeDpDown.getOptions();
			int iListSize = oSize.size();
			for (int i = 0; i < iListSize; i++) {
				// Storing the value of the option
				wait(20);
				sValue = typeDpDown.getOptions().get(i).getText().trim();
				if (sValue.equalsIgnoreCase(text.trim())) {
					typeDpDown.selectByIndex(i);
					break;
				}
			}
		}

	}

	public static int getDropDownSize(WebElement element) {
		Select dropDown = new Select(element);

		List<WebElement> count = dropDown.getOptions();
		return count.size() - 1;
	}
}
