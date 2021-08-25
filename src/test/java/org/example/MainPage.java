package org.example;

import org.openqa.selenium.By;

import static org.example.ChromeDriverCreate.*;
import static org.example.ConfProperties.*;

public class MainPage {
    public static By enterButton = By.xpath("//*[contains(@class,'auth-bar') and contains(text(),'Вход')]");
    public static By loginField = By.xpath("//input[contains(@class,'auth-input') and contains(@type,'text')]");
    public static By passwordField = By.xpath("//input[contains(@class,'auth-input') and contains(@type,'password')]");
    public static By enteringButton = By.xpath("//button[contains(@class,'auth-button') and contains(@type,'submit')]");
    public static By checkMainPage = By.xpath("//div[contains(@class,'footer-style') and contains(@class,'copy')]");

    public static void isPresent(By xPathLocator) {
        for (int i = 0; driver.findElements(xPathLocator).size() <= 0 & i < getIterationNumber(); i++) {
            createWait(xPathLocator);
            driver.navigate().refresh();
        }
    }

    public static void clickEnter(By xPathLocator) {
        createWait(xPathLocator);
        driver.findElement(xPathLocator).click();
    }

    public static void inputLogin(String login) {
        driver.findElement(loginField).sendKeys(login);
    }

    public static void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public static void clickEntering(By xPathLocator) {
        driver.findElement(xPathLocator).click();
        System.out.println("Авторизация выполнена...");
        createWait(CategorySelectPage.userAvatar);
    }

    public static boolean checkText(By xPathLocator) {
        createWait(xPathLocator);
        return driver.findElement(xPathLocator).getText().equals("© 2001—2021 Onlíner");
    }
}
