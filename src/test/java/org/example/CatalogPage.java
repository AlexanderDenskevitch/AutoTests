package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.example.ConfProperties.createWait;
import static org.example.ChromeDriverCreate.driver;


public class CatalogPage {
    public static By popularCategoryElements = By.xpath("//div[@class='catalog-bar-main']//li");
    public static By catalogBtn = By.xpath("//*[contains(@class,b-main-navigation)]/span[contains(@class,'b-main-navigation') and contains(text(),'Каталог')]");
    static String randCatText;

    public static void clickCatalog(By xPathLocator) {
        createWait(xPathLocator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(xPathLocator));
        driver.findElement(xPathLocator).click();
    }

    public static void randPopularCatClick(By xPathLocator) {
        createWait(xPathLocator);
        List<WebElement> elements = driver.findElements(xPathLocator);
        Random random = new Random();
        int categoryNumber = random.nextInt(elements.size() + 1) + 1;
        randCatText = driver.findElement(By.xpath(String.format("//div[@class='catalog-bar-main']//li[%d]", categoryNumber))).getText();
        driver.findElement(By.xpath(String.format("//div[@class='catalog-bar-main']//li[%d]", categoryNumber))).click();
        System.out.println("Выбранная категория: " + randCatText);
        CategorySelectPage.selectTitle = driver.findElement(CategorySelectPage.categoryTitle).getText();
    }
}

