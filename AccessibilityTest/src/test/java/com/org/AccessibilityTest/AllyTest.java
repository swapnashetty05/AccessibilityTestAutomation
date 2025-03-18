package com.org.AccessibilityTest;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AllyTest {
	WebDriver driver;
	private static final URL scriptUrl = AllyTest.class.getResource("/axe.min.js");
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
	}

	@Test
	public void testAmazon() {

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations;
		try {
			violations = responseJSON.getJSONArray("violations");

			if (violations.length() == 0) {
				Assert.assertTrue(true,"No violations found");
			} else {
				AXE.writeResults("amazonAllyTest", responseJSON);
				Assert.assertTrue(false, AXE.report(violations));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testWithSelector() {

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).include("title").analyze();

		JSONArray violations;
		try {
			violations = responseJSON.getJSONArray("violations");

			if (violations.length() == 0) {
				Assert.assertTrue(true,"No violations found");
			} else {
				AXE.writeResults("testWithSelector", responseJSON);
				Assert.assertTrue(false, AXE.report(violations));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testWithWebElement() {

		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
				.analyze(driver.findElement(By.linkText("Customer Service")));

		JSONArray violations;
		try {
			violations = responseJSON.getJSONArray("violations");

			if (violations.length() == 0) {
				Assert.assertTrue(true,"No violations found");
			} else {
				AXE.writeResults("testWithWebElement", responseJSON);
				Assert.assertTrue(false, AXE.report(violations));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

