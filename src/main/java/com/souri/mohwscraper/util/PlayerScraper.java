package com.souri.mohwscraper.util;

import com.souri.mohwscraper.exception.IncorrectURLException;
import com.souri.mohwscraper.exception.NoSuchWebElementException;
import com.souri.mohwscraper.exception.TimeoutException;
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
import java.util.ArrayList;
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
        } catch (NoSuchWebElementException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch player's ID");
        } catch (NullPointerException e) {
            throw new IncorrectURLException("Element not found: Couldn't fetch player's ID; check if given parameters are correct e.g player account exists");
        }
        return playerID;
    }

    private String getPage(String url) {
        //TODO: Transition to a different, more lightweight webdriver
        String chromedriverEnvVar = System.getenv("CHROMEDRIVER_PATH");
        String chromeLocationEnvVar = System.getenv("GOOGLE_CHROME_BIN");
        System.setProperty("webdriver.chrome.driver", chromedriverEnvVar);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromeLocationEnvVar);

        options.addArguments("--headless", "--incognito");
        options.addArguments("start-maximized",
                "enable-automation",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-browser-side-navigation",
                "--disable-gpu");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        //TODO: Open browser in selenium webdriver with a default tab on startup and sequentially open and close created tabs to speed up the process; keep the session
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            String error = "Timeout error: Couldn't open given link: " + url + " via webdriver";
            driver.close();
            throw new com.souri.mohwscraper.exception.TimeoutException(error);
        }
        if (!driver.getCurrentUrl().equals(url)) {
            driver.close();
            throw new IncorrectURLException("Incorrect URL: Final URL is different than expected; check if given parameters are correct e.g player account exists");
        }
        try {
            long pageLoadTimeout = TimeUnit.SECONDS.toSeconds(50);
            new WebDriverWait(driver, pageLoadTimeout).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException e) {
            driver.close();
            throw new com.souri.mohwscraper.exception.TimeoutException("Timeout error: Document not ready on time");
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
            List<Element> playerOverviewStats = doc.getElementById("overview-numbers").getElementsByTag("li");
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

    public List<Map<String, String>> getClassStats(String playerName) {
        String playerID = fetchPlayerID(playerName);

        String url = baseUrl + "/soldier/" + playerName + "/detailed/" + playerID + "/pc";
        Document doc = Jsoup.parse(getPage(url));
        List<Map<String, String>> classStats = new ArrayList<>();

        try {
            List<Element> statsRows = doc.getElementById("mohw-stats-detailed-kit-stats").getElementsByTag("tr");
            if (statsRows.isEmpty()) {
                throw new NoSuchWebElementException("");
            }
            for (int i = 0; i < statsRows.get(0).childrenSize() - 1; i++) {
                classStats.add(new HashMap<>());
            }
            List<Element> classTypes = statsRows.get(0).getElementsByTag("img");
            classStats.
                    forEach(e -> {
                        e.put("Class", classTypes.get(0).attr("data-tooltip"));
                        classTypes.remove(0);
                    });
            statsRows.remove(0);
            statsRows.forEach(e -> {
                        List<Element> cells = e.getElementsByTag("td");
                            for (int i = 1; i < cells.size(); i++) {
                                classStats.get(i - 1).put(cells.get(0).text(), cells.get(i).text());
                            }
                        });
        }
        //Handling situations when mohw-stats-detailed-kit-stats is not found and throws NullPointerException
        catch(NoSuchWebElementException | NullPointerException e) {
            throw new NoSuchWebElementException("Element not found: Couldn't fetch class statistics");
        }

        return classStats;
    }
}
