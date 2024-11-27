package dci.j24e01.TravelBlog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    private final String API_KEY = "2e366e5242fbe23751ff53e2436a789b";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Map<String, Object> getWeather(String cityName) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric") // Celsius
                .toUriString();

        return restTemplate.getForObject(url, Map.class); // Map for dynamic parsing
    }
}
