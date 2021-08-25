package org.example;

import org.openqa.selenium.By;
import static org.example.ChromeDriverCreate.driver;

public class CategorySelectPage {
    public static By categoryTitle = By.xpath("//div[@class='schema-header']//h1");
    public static By userAvatar = By.xpath("//*[contains(@class,'js-header-user-avatar')]");
    public static By logoutLink = By.xpath("//a[contains(@data-bind,'click')]/parent::div[contains(@class,'logout')]/*");

    static String selectTitle;

    public static void logout(){
        driver.findElement(CategorySelectPage.userAvatar).click();
        ConfProperties.createWait(CategorySelectPage.logoutLink);
        driver.findElement(CategorySelectPage.logoutLink).click();
    }

}