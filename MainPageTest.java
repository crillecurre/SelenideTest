package com.example.demo2;

import com.codeborne.selenide.*;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.SetValueOptions.withText;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainPageTest {
    MainPage mainPage = new MainPage();


    @BeforeAll
    static void setup() {
        Configuration.holdBrowserOpen = true;
    }

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

    }












    @Test
    public void findCourseSyllabus() {
        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press button "Kursrum"
        mainPage.kursrum.click();


        // Get the window handle of the main window
        /*String mainWindowHandle = WebDriverRunner.getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = WebDriverRunner.getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                WebDriverRunner.getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }*/

        // Get the window handle of the main window
        String mainWindowHandle = WebDriverRunner.getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = WebDriverRunner.getWebDriver().getWindowHandles();

// Switch to the first popup window
        String firstPopupWindowHandle = "";
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                firstPopupWindowHandle = windowHandle;
                WebDriverRunner.getWebDriver().switchTo().window(firstPopupWindowHandle);
                break;
            }
        }

        //Press "Kurser" button in canvas when the button is visible
        mainPage.kurser.shouldBe(visible).click();

        //Press on Test av IT course
        mainPage.testavit.click();

        //Press on "Moduler" when the button is visible
        mainPage.moduler.shouldBe(visible).click();

        //Find course syllabus choice in modules
        mainPage.courseSyllabus.shouldBe(visible).click();

        WebDriverRunner.getWebDriver().close();

// Switch to the second popup window
        String secondPopupWindowHandle = "";
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle) && !windowHandle.equals(firstPopupWindowHandle)) {
                secondPopupWindowHandle = windowHandle;
                WebDriverRunner.getWebDriver().switchTo().window(secondPopupWindowHandle);
                break;
            }
        }


       open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan&termin=V23)";
        mainPage.pdf.click();
    }













































    @Test
    void finalExaminationInformation() {

        //Opens the webpage for ltu.se
        open("https://www.ltu.se/");

        //Accept cookies
        mainPage.cookieButton.click();
        //Verify that we are on the right page
        String url = WebDriverRunner.url();
        Assertions.assertEquals(url, "https://www.ltu.se/");

        mainPage.studentButton.shouldBe(visible).click();
        //Verify that we are on the right page
        String page = WebDriverRunner.url();
        Assertions.assertEquals(page, "https://www.ltu.se/student");

        mainPage.loggaIn.shouldBe(visible).click();

        // Read Facebook credentials from JSON file
        String email = null;
        String password = null;
        File jsonFile = new File("C:\\temp\\ltu.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {

        }

        //Send the credentials into the "användarid" and "lösenord" field
        mainPage.inputUsername.sendKeys(email);
        mainPage.inputPassword.sendKeys(password);

        //Press button to login
        mainPage.inputSubmit.click();

        //Press button "Kursrum"
        mainPage.kursrum.click();


        // Get the window handle of the main window
        String mainWindowHandle = WebDriverRunner.getWebDriver().getWindowHandle();

// Get the handles of all windows currently open
        Set<String> allWindowHandles = WebDriverRunner.getWebDriver().getWindowHandles();

// Switch to the popup window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                WebDriverRunner.getWebDriver().switchTo().window(windowHandle);
                break;
            }
        }

        //Press "Kurser" button in canvas when the button is visible
        mainPage.kurser.shouldBe(visible).click();

        //Press on Test av IT course
        mainPage.testavit.click();

        //Press on "Moduler" when the button is visible
        mainPage.moduler.shouldBe(visible).click();

        //Press on button to get information about finalExamination
        mainPage.finalExamination.shouldBe(visible).click();

        /*The test is due to 30th of May between 9:00 and 14:00 so we want to verify that the
        Final Examination Information includes this
        */
        Assertions.assertTrue(mainPage.info.text().contains("30th"));
        Assertions.assertTrue(mainPage.info.text().contains("May"));
        Assertions.assertTrue(mainPage.info.text().contains("9:00"));
        Assertions.assertTrue(mainPage.info.text().contains("14:00"));



    }


}


































    
