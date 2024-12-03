package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.services.DetailDataService;
import dci.j24e01.TravelBlog.services.VacationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class editDetailDataController {

    private final VacationService vacationService;
    private final DetailDataService detailDataService;

    public editDetailDataController(VacationService vacationService, DetailDataService detailDataService) {
        this.vacationService = vacationService;
        this.detailDataService = detailDataService;
    }

    @GetMapping("/admin/editDetails")
    public String editDetails(Model model) {
        List<VacationPoint> vacationPoints = vacationService.getAllVacationPoints();
        model.addAttribute("vacationPoints", vacationPoints);
        return "admin/editDetails";
    }

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

        // Save the updated DetailData
        detailDataService.saveDetailData(detailData);

        // Redirect back to the admin page with a success message
        return "redirect:/admin/editDetails?success";
    }



}
