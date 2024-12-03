package dci.j24e01.TravelBlog.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GeocodingService {

    public double[] getCoordinates(String city, String country) {
        String url = String.format(
                "https://nominatim.openstreetmap.org/search?q=%s,%s&format=json&addressdetails=1&limit=1",
                city, country
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            GeocodingResponse[] response = restTemplate.getForObject(url, GeocodingResponse[].class);
            if (response != null && response.length > 0) {
                double lat = Double.parseDouble(response[0].getLat());
                double lon = Double.parseDouble(response[0].getLon());
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
            throw new RuntimeException("Geocoding failed for " + city + ", " + country, e);
        }
        throw new RuntimeException("No coordinates found for " + city + ", " + country);
    }

    private static class GeocodingResponse {
        @JsonProperty("lat")
        private String lat;

        @JsonProperty("lon")
        private String lon;

        // Getters and setters
        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}



