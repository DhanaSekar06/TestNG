package com.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.utilities.Utiles;

public class LoginElements extends Utiles {

	public LoginElements() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button.ham")
	public WebElement menuBtn;

	@FindBy(css = "button.mid")
	public WebElement signInBtn;

	@FindBy(id = "login_id")
	public WebElement mobileNoField;

	@FindBy(css = "label#recaptcha-anchor-label")
	public WebElement robotCheckBox;

	@FindBy(xpath = "// button[@type='submit']")
	public WebElement continueBtn;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement otpSubmitBtn;

	@FindBy(partialLinkText = " Gaana Plus")
	public WebElement ganaPluseBtn;

	@FindBy(xpath = "// iframe[@title='reCAPTCHA']")
	public WebElement iFrameCaptcha;

	//
	//
	//
	//

}