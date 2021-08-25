package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import static org.example.ChromeDriverCreate.*;


public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    protected static int iterationNumber = 5;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/Test.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static int getIterationNumber() {
        return iterationNumber;
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void createWait(By webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(webElement));
    }
    public static void createWait() {
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
    }
}
