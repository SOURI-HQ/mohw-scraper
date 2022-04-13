package com.souri.mohwscraper.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import javax.swing.event.ListDataEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Component
public class PlayerScraper {

    private final String baseUrl = "https://battlelog.battlefield.com/mohw/en";

    private String fetchPlayerID(String playerName) {
        try {
            Document doc = Jsoup.connect(baseUrl + "/user/" + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list");
            if (playerAccount != null && playerAccount.childrenSize() > 0) {
                String playerID = playerAccount.children().first().attr("data-id");
                return playerID;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPage(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dan\\IdeaProjects\\mohw-scraper\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
//        WebDriverWait wait = new WebDriverWait(driver, 8);
        try {
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mohw-overview")));
            long pageLoadTimeout = TimeUnit.SECONDS.toSeconds(10);
            new WebDriverWait(driver, pageLoadTimeout).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }

        String pageSource = driver.getPageSource();
        //driver.close();

        return pageSource;
    }

    public Map<String, String> getPlayerOverview(String playerName) {
        String playerID = fetchPlayerID(playerName);

        Map<String, String> playerOverview = new HashMap<>();

        try {
            String url = baseUrl + "/soldier/" + playerName + "/stats/" + playerID + "/pc";
            Document doc = Jsoup.parse(getPage(url));

            List<Element> playerOverviewStats = doc.getElementById("overview-numbers").getElementsByTag("li");

            playerOverviewStats.forEach(n -> playerOverview.put(n.getElementsByTag("h4").text(), n.getElementsByTag("p").text()));
            playerOverview.put("Rank", doc.getElementById("overview-rank-number").text());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return playerOverview;
    }

    public List<Map<String, String>> getPlayerDetails(String playerName) {
        String playerID = fetchPlayerID(playerName);

        //TODO: IMPORTANT! Change back to lists or figure out how to handle nulls a different way
        List<Map<String, String>> playerDetails = new ArrayList<>();
//        try {
            String url = baseUrl + "/soldier/" + playerName + "/detailed/" + playerID + "/pc";
            Document doc = Jsoup.parse(getPage(url));
            List<Element> playerDetailsStats = doc.getElementById("mohw-stats-detailed-stats").getElementsByTag("li");
            if (playerDetailsStats == null) {
                throw new NullPointerException();
            }
            //playerDetailsStats.forEach(n -> playerDetails.put(n.text().substring(0, n.text().length() - n.getElementsByTag("strong").text().length()), n.getElementsByTag("strong").text()));

            playerDetails.add(new HashMap<>() {{
                playerDetailsStats.forEach(n -> put(n.text().substring(0, n.text().length() - n.getElementsByTag("strong").text().length()),n.getElementsByTag("strong").text()));
            }});
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
        return playerDetails;
    }
}
