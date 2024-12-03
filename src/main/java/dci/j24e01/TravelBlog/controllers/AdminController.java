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

import java.util.List;

@Controller
public class AdminController {

    private final VacationService vacationService;
    private final DetailDataService detailDataService;

    public AdminController(VacationService vacationService, DetailDataService detailDataService) {
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
    public String saveDetails(@ModelAttribute("detailData") DetailData detailData) {
        detailDataService.saveDetailData(detailData);
        return "redirect:/admin/editDetails?success";
    }


}
