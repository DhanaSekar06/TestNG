package com.page.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.utilities.Utiles;

public class GanaPluseElements extends Utiles {

	public GanaPluseElements() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(partialLinkText = " Gaana Plus")
	public WebElement ganaPluseBtn;

	@FindBy(css = "span.new")
	public List<WebElement> prices;

	@FindBy(css = "span.dur")
	public List<WebElement> plans;

	@FindBy(xpath = "//button[text()='Buy Now']")
	public WebElement buyNowBtn;
	//
	//
	//

	@FindBy(css = "label#recaptcha-anchor-label")
	public WebElement robotCheckBox;

	@FindBy(xpath = "// button[@type='submit']")
	public WebElement continueBtn;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement otpSubmitBtn;

	@FindBy(xpath = "// iframe[@title='reCAPTCHA']")
	public WebElement iFrameCaptcha;

	public void printPrice() {

		for (WebElement web : prices) {
			System.out.println(web.getText().toString());
		}
	}

	public void printPlans() {

		for (WebElement webElement : plans) {
			System.out.println(webElement.getText().toString());
		}

	}

}