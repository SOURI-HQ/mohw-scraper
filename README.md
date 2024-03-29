# MoHW Battlelog Scraper

A REST API providing scraped and captured data about players and servers statistics from EA's official Medal of Honor Warfighter battlelog website - https://battlelog.battlefield.com/mohw/

---
### Important!
On 16th of February 2023, EA officially shut down multiplayer aspects of Medal of Honor Warfighter. As a result of Blaze server and The Game Statistics Engine on Battlelog being offline, MoHW Battlelog Scraper is rendered unavailable.

---
~~Hosted on [heroku](https://www.heroku.com), shared on [Rapid API](https://rapidapi.com), MoHW Battlelog Scraper can be found **[here](https://rapidapi.com/SOURI/api/mohw-battlelog-scraper/)** 
along with detailed [Swagger documentation](https://mohw-battlelog-scraper.herokuapp.com/swagger-ui.html)~~.

## :heavy_check_mark: Endpoints

MoHW Battlelog Scraper captures data flowing from `https://battlelog.battlefield.com/mohw/mohwoverviewpopulate/{player_ID}/None/1/` to the main Battlelog page.
A different scraping approach is at place as well which requires initializing a selenium web driver for Chrome along with the browser itself.
As the driver isn't that lightweight, the first approach has made it to the final service implementation.
### Player
| Resource       | Available endpoints             | Description                                       |
|----------------|---------------------------------|---------------------------------------------------|
| PlayerOverview | player/{name}/getPlayerOverview | Returns provided player's general stats overview. |
| PlayerDetails  | player/{name}/getPlayerDetails  | Returns provided player's more detailed stats     |
| PlayerClasses  | player/{name}/getPlayerClasses  | Returns provided player's class type stats        |

### Server
| Resource         | Available endpoints        | Description                                    |
|------------------|----------------------------|------------------------------------------------|
| getActiveServers | server/getActiveServers    | Returns a list of currently populated servers. |
| getServers       | server/getAvailableServers | Returns provided player's more detailed stats. |

## :heavy_check_mark: Battlelog-MoHW Scraper

The application is entirely dependent on provided by EA free social platform [Battlelog](https://battlelog.battlefield.com/bf4/pl/gate/).
Any changes on the website, though quite unlikely due to it being partly discontinued, may render the scraper dysfunctional.

In order to obtain any player statistics the app has to first fetch player's global ID from `https://battlelog.battlefield.com/mohw/en/user/{player_name}` using Jsoup.
It then uses the ID to get a response from `https://battlelog.battlefield.com/mohw/mohwoverviewpopulate/{player_ID}/None/1/` in JSON format which is populating Battlelog with all the needed content.
Once the scraper obtained that it is only a matter of processing the data and returning it via the REST API.
## :heavy_check_mark: Used technologies

- Java 17
- Spring Boot 2.6.3
- Maven 3.8.4
- Swagger 2.9.2
- Jsoup 1.14.3
- Selenium 3.141.59
- JUnit 5.8.2

