package dci.j24e01.TravelBlog.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    private final String apiKey;
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherService() {
        // Load the API key from the .env file
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .load();
        this.apiKey = dotenv.get("WEATHER_API_KEY");
    }

    public Map<String, Object> getWeather(String cityName) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q", cityName)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        return restTemplate.getForObject(url, Map.class);
    }
}
