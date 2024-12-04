package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.HeroSettings;
import dci.j24e01.TravelBlog.models.PendingLocation;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.repositories.HeroSettingsRepository;
import dci.j24e01.TravelBlog.repositories.PendingLocationRepository;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import dci.j24e01.TravelBlog.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin_panel")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VacationPointRepository vacationPointRepository;

    @Autowired
    private PendingLocationRepository pendingLocationRepository;

    @Autowired
    private HeroSettingsRepository heroSettingsRepository;

    @GetMapping
    public String adminPanel(Model model) {
        HeroSettings heroSettings = heroSettingsRepository.findTopByOrderByIdDesc();
        List<VacationPoint> locations = vacationPointRepository.findAll();
        List<PendingLocation> pendingLocations = pendingLocationRepository.findAll();

        model.addAttribute("heroSettings", heroSettings);
        model.addAttribute("locations", locations);
        model.addAttribute("pendingLocations", pendingLocations);
        return "admin/admin_panel";
    }

    @PostMapping("/hero_settings")
    public String saveHeroSettings(@RequestParam("background_image_url") String backgroundImageUrl,
                                   @RequestParam("title") String title, Model model) {

        HeroSettings heroSettings = heroSettingsRepository.findAll().stream().findFirst().orElse(new HeroSettings());

        heroSettings.setBackgroundImageUrl(backgroundImageUrl);
        heroSettings.setTitle(title);

        heroSettingsRepository.save(heroSettings);

        model.addAttribute("heroSettings", heroSettings);

        return "/admin/admin_panel";
    }

    @GetMapping("/edit_location/{id}")
    public String editLocation(@PathVariable long id, Model model) {
        VacationPoint point = adminService.findVacationPointById(id);
        model.addAttribute("city", point.getCity());
        model.addAttribute("country", point.getCountry());
        model.addAttribute("description", point.getDescription());
        model.addAttribute("startDate", point.getStartDate());
        model.addAttribute("endDate", point.getEndDate());
        model.addAttribute("id", id);

        return "/admin/edit_vacation_point";
    }

    @PostMapping("/edit_location")
    public String editLocation(@RequestParam Long id,
                               @RequestParam String city,
                               @RequestParam String country,
                               @RequestParam String description,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
                               @RequestParam MultipartFile[] photos) {

        adminService.saveVacationPoint(id, city, country, description, startDate, endDate, photos);
        return "redirect:/admin_panel";
    }

    @GetMapping("delete_location/{id}")
    public String deleteLocation(@PathVariable long id) {
        adminService.deleteVacationPoint(id);
        return "redirect:/admin_panel";
    }
}