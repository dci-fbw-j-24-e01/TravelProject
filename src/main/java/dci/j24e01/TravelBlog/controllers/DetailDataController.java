package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.services.DetailDataService;
import dci.j24e01.TravelBlog.services.VacationService;
import dci.j24e01.TravelBlog.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class DetailDataController {

    private final VacationService vacationService;
    private final DetailDataService detailDataService;
    private final WeatherService weatherService;

    @Autowired
    public DetailDataController(VacationService vacationService, DetailDataService detailDataService, WeatherService weatherService) {
        this.vacationService = vacationService;
        this.detailDataService = detailDataService;
        this.weatherService = weatherService;
    }

    // Display all vacation points with editable details
    @GetMapping("/admin/editDetails")
    public String showEditDetails(Model model) {
        List<VacationPoint> vacationPoints = vacationService.getAllVacationPoints();
        for (VacationPoint point : vacationPoints) {
            if (point.getDetailData() == null) {
                DetailData detailData = new DetailData();
                detailData.setVacationPoint(point);
                point.setDetailData(detailData);
            }
        }
        model.addAttribute("vacationPoints", vacationPoints);
        return "admin/editDetails";
    }

    // Save details from a VacationPoint to a DetailData
    @PostMapping("/admin/saveDetails")
    public String saveDetails(
            @RequestParam Long pointId,
            @RequestParam String cityName,
            @RequestParam String countryName,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String description,
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        // Fetch the VacationPoint by ID
        VacationPoint vacationPoint = vacationService.getVacationPointById(pointId);

        // Fetch the associated DetailData or create a new one if it doesn't exist
        DetailData detailData = vacationPoint.getDetailData();
        if (detailData == null) {
            detailData = new DetailData();
            detailData.setVacationPoint(vacationPoint);
        }

        // Populate fields for DetailData from the form
        detailData.setCityName(cityName);
        detailData.setCountryName(countryName);
        detailData.setStartDate(startDate);
        detailData.setEndDate(endDate);
        detailData.setDescription(description);
        detailData.setLatitude(latitude);
        detailData.setLongitude(longitude);


        // Update the photos collection
        detailData.getPhotos().clear(); // Clear existing photos. Needed in case we wanted to update the photos List
        detailData.getPhotos().addAll(vacationPoint.getPhotos()); // Add all photos from VacationPoint


        // Save the updated DetailData
        detailDataService.saveDetailData(detailData);
        System.out.println(detailData);

        // Redirect back to the admin page with a success message
        return "redirect:/admin/editDetails?success";
    }

    // Display detailed information for a specific VacationPoint
    @GetMapping("/detail/{id}")
    public String getDetailById(@PathVariable Long id, Model model) {
        System.out.println("Received request for detail page with ID: " + id); // for debugging
        DetailData detailData = detailDataService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DetailData not found"));

        // Fetch weather data
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
        model.addAttribute("photos", detailData.getPhotos());
        model.addAttribute("weatherData", weatherData);

        return "detail";
    }
}
