package com.test.gaana;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.utilities.Utiles;
import com.page.elements.GanaPluseElements;
import com.page.elements.LoginElements;

public class AssignmentQA extends Utiles {
	LoginElements login;
	GanaPluseElements ganapluse;

	@BeforeTest
	public void lunchBrwoser() {
		browserLaunch("chrome");
		login = new LoginElements();
	}

	@Test(priority = 3, enabled = true)
	public void login() throws InterruptedException {
		Thread.sleep(4000);
		// click(login.menuBtn);
		// click(login.signInBtn);
		type(login.mobileNoField, "8489829462");
		switchToFrame(login.iFrameCaptcha);
		checkCheckBoxByJavaScript(login.robotCheckBox);
		switchBackFromFrame();
		click(login.continueBtn);
		wait(30000);
		click(login.otpSubmitBtn);

	}

	@Test(priority = 2)

	public void GaanaBtnPlanValidation() {
		click(ganapluse.ganaPluseBtn);
		controlon_nth_Window(1);
		ganapluse.printPlans();
		ganapluse.printPrice();
		click(ganapluse.buyNowBtn);

	}

}