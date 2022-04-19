package com.souri.mohwscraper.util;

import com.souri.mohwscraper.exceptions.IncorrectURLException;
import com.souri.mohwscraper.exceptions.NoSuchWebElementException;
import com.souri.mohwscraper.exceptions.TimeoutException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class PlayerScraper {

    private final String baseUrl = "https://battlelog.battlefield.com/mohw/en";

    private String fetchPlayerID(String playerName) {
        String playerID = "";
        try {
            Document doc = Jsoup.connect(baseUrl + "/user/" + playerName).get();
            Element playerAccount = doc.getElementById("soldier-list");
            playerID = playerAccount.children().first().attr("data-id");
            if (playerID.equals("")) {
                throw new NoSuchWebElementException("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchWebElementException | NullPointerException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch player's ID");
        }

        return playerID;
    }

    private String getPage(String url) {
        String chromedriverEnvVar = System.getenv("Chromedriver");
        System.setProperty("webdriver.chrome.driver", chromedriverEnvVar);
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless", "--incognito");
        options.addArguments("start-maximized",
                "enable-automation",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-browser-side-navigation",
                "--disable-gpu");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            String error = "Timeout error: Couldn't open given link: " + url + " via webdriver";
            throw new TimeoutException(error);
        }
        if (!driver.getCurrentUrl().equals(url)) {
            throw new IncorrectURLException("Incorrect URL: Final URL is different than expected; check if given parameters are correct e.g player account exists");
        }
        try {
            long pageLoadTimeout = TimeUnit.SECONDS.toSeconds(20);
            new WebDriverWait(driver, pageLoadTimeout).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout error: Document not ready on time");
        }
        String pageSource = driver.getPageSource();
        driver.close();

        return pageSource;
    }

    public Map<String, String> getPlayerOverview(String playerName) {
        String playerID = fetchPlayerID(playerName);
        Map<String, String> playerOverview = new HashMap<>();

        String url = baseUrl + "/soldier/" + playerName + "/stats/" + playerID + "/pc";
        Document doc = Jsoup.parse(getPage(url));
        try {
            List<Element> playerOverviewStats = doc.getElementById("overview-numbders").getElementsByTag("li");
            String rank = doc.getElementById("overview-rank-number").text();
            if (playerOverviewStats.isEmpty()) {
                throw new NoSuchWebElementException("");
            }
            playerOverviewStats.forEach(n -> playerOverview.put(n.getElementsByTag("h4").text(), n.getElementsByTag("p").text()));
            playerOverview.put("Rank", rank);
        } catch (NoSuchWebElementException | NullPointerException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch player's overview details");
        }

        return playerOverview;
    }

    public Map<String, String> getPlayerDetails(String playerName) {
        String playerID = fetchPlayerID(playerName);

        Map<String, String> playerDetails = new HashMap<>();
        String url = baseUrl + "/soldier/" + playerName + "/detailed/" + playerID + "/pc";
        Document doc = Jsoup.parse(getPage(url));
        try {
            List<Element> playerDetailsStats = doc.getElementById("mohw-stats-detailed-stats").getElementsByTag("li");
            //Handling situations when no tag element in mohw-stats-detailed-stats is found
            if (playerDetailsStats.isEmpty()) {
                throw new NoSuchWebElementException("");
            }
            playerDetailsStats.forEach(n -> playerDetails.put(n.text().substring(0, n.text().length() - n.getElementsByTag("strong").text().length()), n.getElementsByTag("strong").text()));
        }
        //Handling situations when mohw-stats-detailed-stats is not found and throws NullPointerException
        catch(NoSuchWebElementException | NullPointerException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch player's detailed statistics");
        }
        return playerDetails;
    }
}
