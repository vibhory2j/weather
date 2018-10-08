How to Build and Run
====================
1. mvn clean package
2. docker build -t weather-challenge:1.0
3. Get an api token from openweather api.
3. docker run -d -e OPEN_WEATHER_API_URL=http://api.openweathermap.org/data/2.5/forecast? -e OPEN_WEATHER_TOKEN=<token-from-openweathermap> -p 8081:8080 weather-challenge:1.0(please ensure port 8080 is available on your local machine)
4. Swagger Rest API documentation generated at http://localhost:8081/swagger-ui.html. Don't consider it fully complete.
5. Javadoc documentation generated at target/apidocs/index.html
Alternatively you can run it from your commandline - java -jar target/weather-0.0.1-SNAPSHOT.jar

Sample API Calls
================
1. http://localhost:8081/api/v1/data?city=Berlin 
2. http://localhost:8081/api/v1/data?city=abc 
3. http://localhost:8081/api/v1/data?city=London

Assumptions
===========
1. Forecast response provided by the Open Weather Api is sorted by date and time.
2. Implemented a basic in memory cache using ConcurrentHashMap.
3. Unit and Integration tests are very basic ones. Can be better.
4. The current api averages statistics current plus following two days. Averages are not done on statistics of same day.
