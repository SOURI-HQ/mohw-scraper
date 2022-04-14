package com.souri.mohwscraper.util;

import com.souri.mohwscraper.exceptionhandler.exceptions.IncorrectURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

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

    private String getPage(String url) throws IncorrectURLException {
        String chromedriverEnvVar = System.getenv("Chromedriver");
        System.setProperty("webdriver.chrome.driver", chromedriverEnvVar);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            throw new TimeoutException("Couldn't open the link");
        }

        if (!driver.getCurrentUrl().equals(url)) {
            throw new IncorrectURLException("Player account doesn't exist");
        }

        try {
            long pageLoadTimeout = TimeUnit.SECONDS.toSeconds(10);
            new WebDriverWait(driver, pageLoadTimeout).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            throw new TimeoutException("Document not ready");
        }

        String pageSource = driver.getPageSource();
        driver.close();

        return pageSource;
    }

    public Map<String, String> getPlayerOverview(String playerName) {
        String playerID = fetchPlayerID(playerName);
        Map<String, String> playerOverview = new HashMap<>();

//        try {
            String url = baseUrl + "/soldier/" + playerName + "/stats/" + playerID + "/pc";
            Document doc = Jsoup.parse(getPage(url));

            //TODO: Come up with a solution to solving NullPointerException exceptions
            List<Element> playerOverviewStats = doc.getElementById("overview-numbers").getElementsByTag("li");
            String rank = doc.getElementById("overview-rank-number").text();
            if (playerOverviewStats.isEmpty() || rank.isEmpty()) {
                throw new NoSuchElementException("Overview stats not found");
            }

            playerOverviewStats.forEach(n -> playerOverview.put(n.getElementsByTag("h4").text(), n.getElementsByTag("p").text()));
            playerOverview.put("Rank", rank);

//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
        return playerOverview;
    }

    public Map<String, String> getPlayerDetails(String playerName) {
        String playerID = fetchPlayerID(playerName);

        Map<String, String> playerDetails = new HashMap<>();
        String url = baseUrl + "/soldier/" + playerName + "/detailed/" + playerID + "/pc";
        Document doc = Jsoup.parse(getPage(url));
        List<Element> playerDetailsStats = doc.getElementById("mohw-stats-detailed-stats").getElementsByTag("li");
        if (playerDetailsStats.isEmpty()) {
            throw new NoSuchElementException("Detailed stats not found");
        }
        playerDetailsStats.forEach(n -> playerDetails.put(n.text().substring(0, n.text().length() - n.getElementsByTag("strong").text().length()), n.getElementsByTag("strong").text()));

        return playerDetails;
    }
}
