package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

import static org.example.ConfProperties.*;

public class VkApiUtils {
    static String postMethod = getProperty("wallPostMethod");
    static String editMethod = getProperty("wallEditMethod");
    static String createCommentMethod = getProperty("createComment");
    static String owner_id = "owner_id=" + getProperty("owner_id");
    static String user_id = getProperty("user_id");
    static String message = getRandomString(50);
    static String messageForEdit = getRandomString(40);
    static String commentMessage = getRandomString(25);
    static String access_token = getProperty("access_token");
    static String apiServerUrl = getProperty("VKapiServer");
    static String apiVersion = getProperty("VKapiVersion");
    static String isLikedMethod = getProperty("isLikedMethod");
    static String wallDeleteMethod = getProperty("wallDeleteMethod");
    static int post_id;
    static int media_id;
    static String photo_data;

    public static void addMessageOnWall() throws ParseException, MalformedURLException {
        String messageForUrl = "&message=" + message;
        URL url = new URL(apiServerUrl + postMethod + owner_id + messageForUrl + access_token + apiVersion);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(ResponseReader.parseUrl(url));
        JSONObject root = (JSONObject) obj.get("response");
        post_id = ((Long) root.get("post_id")).intValue();
        VkMainPage.postField = By.xpath("//div[contains(@id,'post670183693_" + VkApiUtils.post_id + "')]//div[1 and contains(@class,'wall_text')]");
        VkMainPage.postAuthor = By.xpath(String.format("//div[contains(@id,'post%s_%d')]//*[contains(@class,'author')]//*[contains(@href,'id%s')]", getProperty("user_id"), post_id, getProperty("user_id")));
        VkMainPage.likeButton = By.xpath(String.format("//*[contains(@id,'post%s_%d')]//*[contains(@class,'PostBottomActionContainer')]", getProperty("user_id"), post_id));
    }

    public static void getMediaID() throws ParseException, MalformedURLException {
        String photosGet = getProperty("photosGetMethod");
        String albumID = "&album_id=wall";
        URL url = new URL(apiServerUrl + photosGet + owner_id + albumID + access_token + apiVersion);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(ResponseReader.parseUrl(url));
        JSONObject root = (JSONObject) obj.get("response");
        JSONArray items = (JSONArray) root.get("items");
        JSONObject testPhoto = (JSONObject) items.get(0);
        media_id = ((Long) testPhoto.get("id")).intValue();
        photo_data = getProperty("owner_id") + "_" + media_id;
    }

    public static void editMessageOnWall() throws MalformedURLException {
        String messageForUrl = "&message=" + messageForEdit;
        String postID = "&post_id=" + post_id;
        String attachments = "&attachments=photo" + photo_data;
        URL url = new URL(apiServerUrl + editMethod + owner_id + postID + messageForUrl + attachments + access_token + apiVersion);
        ResponseReader.parseUrl(url);
        VkMainPage.wallPhoto = By.xpath(String.format("//div[contains(@id,'post%s_%d')]//*[contains(@data-photo-id,'%s_%d')]", getProperty("user_id"), post_id, getProperty("user_id"), media_id));
    }

    public static void addCommentOnWallMessage() throws MalformedURLException {
        createWait();
        String messageForUrl = "&message=" + commentMessage;
        String postID = "&post_id=" + post_id;
        URL url = new URL(apiServerUrl + createCommentMethod + owner_id + postID + messageForUrl + access_token + apiVersion);
        ResponseReader.parseUrl(url);
        VkMainPage.commentAuthor = By.xpath(String.format("//*[contains(@id,'post%s_%d')]//*[contains(@class,'replies_list')]//a[contains(@class,'author')]", getProperty("user_id"), post_id));
    }

    public static boolean checkLike() throws MalformedURLException, ParseException {
        createWait();
        String postID = "&item_id=" + post_id;
        String userID = "user_id=" + user_id;
        String ownerID = "&" + owner_id;
        String type = "&type=post";
        URL url = new URL(apiServerUrl + isLikedMethod + userID + type + ownerID + postID + access_token + apiVersion);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(ResponseReader.parseUrl(url));
        JSONObject root = (JSONObject) obj.get("response");
        return ((Long) root.get("liked")).intValue() == 1;
    }

    public static void deletePost() throws MalformedURLException {
        createWait();
        String postID = "&post_id=" + post_id;
        String ownerID = "&" + owner_id;
        URL url = new URL(apiServerUrl + wallDeleteMethod + ownerID + postID + access_token + apiVersion);
        ResponseReader.parseUrl(url);
    }
}
