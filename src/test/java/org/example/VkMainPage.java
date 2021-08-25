package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.example.ChromeDriverCreate.driver;
import static org.example.ConfProperties.createWait;
import static org.example.ConfProperties.getProperty;

public class VkMainPage {
    static By loginField = By.xpath("//*[contains(@id,'index_email')]");
    static By passwordField = By.xpath("//*[contains(@id,'index_pass')]");
    static By loginButton = By.xpath("//*[contains(@id,'index_login_button')]");
    static By profileLink = By.xpath("//li[contains(@id,'l_pr')]");
    static By postField;
    static By postAuthor;
    static By wallPhoto;
    static By commentAuthor;
    static By likeButton;
    static By noMessageField = By.xpath("//*[contains(@data-ui-tab,'no_posts')]");

    public static void inputLogin(String login) {
        driver.findElement(loginField).sendKeys(login);
    }

    public static void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public static void clickEntering(By xPathLocator) {
        driver.findElement(xPathLocator).click();
        System.out.println("Авторизация выполнена...");
    }

    public static void clickProfile(By xPathLocator) {
        createWait(xPathLocator);
        driver.findElement(xPathLocator).click();
    }

    public static String checkWallText(By xPathLocator) {
        createWait(xPathLocator);
        createWait();
        return driver.findElement(xPathLocator).getText();
    }

    public static String checkUser(By xPathLocator) {
        createWait(xPathLocator);
        System.out.println(driver.findElement(xPathLocator).getAttribute("href"));
        return driver.findElement(xPathLocator).getAttribute("href");
    }

    public static String checkPhoto(By xPathLocator) {
        createWait(xPathLocator);
        return driver.findElement(xPathLocator).getAttribute("data-photo-id");
    }

    public static String checkComment(By xPathLocator) {
        createWait(By.xpath(String.format("//div[contains(@id,'post%s_%d')]//*[contains(@class,'replies_next')]", getProperty("user_id"), VkApiUtils.post_id)));
        driver.findElement(By.xpath(String.format("//div[contains(@id,'post%s_%d')]//*[contains(@class,'replies_next')]", getProperty("user_id"), VkApiUtils.post_id))).click();
        createWait(xPathLocator);
        return driver.findElement(xPathLocator).getAttribute("data-from-id");
    }

    public static void clickLike(By xPathLocator) {
        createWait(xPathLocator);
        driver.findElement(xPathLocator).click();
    }

    public static WebElement checkDeletePost(By xPathLocator) {
        createWait(xPathLocator);
        return driver.findElement(xPathLocator);
    }
}