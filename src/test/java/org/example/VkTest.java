package org.example;

import org.json.simple.parser.ParseException;
import org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.example.ConfProperties.getProperty;
import static org.example.VkMainPage.*;
import static org.example.VkApiUtils.*;

public class VkTest extends ChromeDriverCreate {

    @Test
    public void mainTest() throws ParseException, MalformedURLException {
        driver.get(getProperty("websiteVK"));
        inputLogin(getProperty("loginVK"));
        inputPassword(getProperty("passwordVK"));
        clickEntering(loginButton);
        clickProfile(profileLink);
        addMessageOnWall();
        Assert.assertEquals(checkWallText(postField), message);
        Assert.assertEquals(checkUser(postAuthor), getProperty("userURI"));
        getMediaID();
        editMessageOnWall();
        Assert.assertNotEquals(checkWallText(postField), messageForEdit);
        Assert.assertEquals(checkPhoto(wallPhoto), photo_data);
        addCommentOnWallMessage();
        Assert.assertEquals(checkComment(commentAuthor), getProperty("user_id"));
        clickLike(likeButton);
        Assert.assertTrue(checkLike());
        deletePost();
        Assert.assertNotNull(noMessageField);
    }
}
