package com.org.AccessibilityPlayWright;

import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Rule;
import com.microsoft.playwright.*;

public class NewPlaywrightAllyTest {
	Page page;
	private static final URL scriptUrl = NewPlaywrightAllyTest.class.getResource("/axe.min.js");

	@BeforeTest
	public void setUp() {
		try {
			// Playwright playwright = Playwright.create();

			// Browser browser;
			Playwright playwright = Playwright.create();
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			page = browser.newPage();
			page.navigate("https://www.amazon.com/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void accessibilityTesting() {
		try {
			List<Rule> violations;
			AxeBuilder axePlaywrightBuilder = new AxeBuilder(page);

			AxeResults axeResults = axePlaywrightBuilder.analyze();
			
			violations = axeResults.getViolations() ;

			if (violations.isEmpty()) {
				Assert.assertTrue(true,"No violations found");
			} else {
				
				System.out.println(violations);
				Assert.assertTrue(false);
			}

			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
