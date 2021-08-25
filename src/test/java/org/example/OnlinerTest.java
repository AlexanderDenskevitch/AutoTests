package org.example;

import org.junit.*;

import static org.example.CatalogPage.*;
import static org.example.CategorySelectPage.*;
import static org.example.MainPage.*;
import static org.example.ConfProperties.*;

public class OnlinerTest extends ChromeDriverCreate {

    @Test
    public void openWebsite() {
        driver.get(getProperty("website"));
        Assert.assertTrue(checkText(checkMainPage));
        isPresent(enterButton);
        clickEnter(enterButton);
        inputLogin(getProperty("login"));
        inputPassword(getProperty("password"));
        clickEntering(enteringButton);
        clickCatalog(catalogBtn);
        randPopularCatClick(popularCategoryElements);
        Assert.assertEquals(randCatText, selectTitle);
        logout();
    }
}



