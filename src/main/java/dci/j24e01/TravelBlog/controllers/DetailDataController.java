package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.services.DetailDataService;
import dci.j24e01.TravelBlog.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Controller
public class DetailDataController {

    private final DetailDataService detailDataService;
    private final WeatherService weatherService;

    @Autowired
    public DetailDataController(DetailDataService detailDataService) {
        this.detailDataService = detailDataService;
        this.weatherService = new WeatherService();
    }



    @GetMapping("/detail/{id}")
    public String getDetailById(@PathVariable Long id, Model model) {
        DetailData detailData = detailDataService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail not found"));


        Map<String, Object> weatherData = weatherService.getWeather(detailData.getCityName());
        if (weatherData != null && weatherData.containsKey("main")) {
            Map<String, Object> mainData = (Map<String, Object>) weatherData.get("main");
            if (mainData != null && mainData.containsKey("temp")) {
                Double temp = (Double) mainData.get("temp");
                if (temp != null) {
                    mainData.put("tempFormatted", String.format("%.1f", temp));
                }
            }
        }

        model.addAttribute("detailData", detailData);
        model.addAttribute("weatherData", weatherData);

        return "detail";
    }
}
