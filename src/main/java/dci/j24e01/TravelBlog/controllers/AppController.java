package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.models.Photo;
import dci.j24e01.TravelBlog.models.VacationPointDTO;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import dci.j24e01.TravelBlog.services.AdminService;
import dci.j24e01.TravelBlog.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private VacationService vacationService;
    @Autowired
    private VacationPointRepository vacationPointRepository;

    public VacationPointDTO toDTO(VacationPoint vacationPoint) {
        VacationPointDTO dto = new VacationPointDTO();
        dto.setCity(vacationPoint.getCity());
        dto.setCountry(vacationPoint.getCountry());
        dto.setDescription(vacationPoint.getDescription());
        dto.setLatitude(vacationPoint.getLatitude());
        dto.setLongitude(vacationPoint.getLongitude());
        dto.setStartDate(vacationPoint.getStartDate());
        dto.setEndDate(vacationPoint.getEndDate());
        dto.setPhotos(vacationPoint.getPhotos().stream()
                .map(Photo::getPhotoPath)
                .collect(Collectors.toList()));
        return dto;
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {

        List<VacationPointDTO> approvedPoints = adminService.getAllApprovedVacationPoints()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());


        model.addAttribute("vacationPoints", approvedPoints);
        model.addAttribute("approvedVacationPoints", vacationPointRepository.findAllByApprovedTrue());

        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();
        model.addAttribute("loggedIn", isLoggedIn);
        model.addAttribute("approvedVacationPoints", adminService.getAllApprovedVacationPoints());
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/delete")
    public String deleteVacationPoint(@RequestParam Long id) {
        adminService.deleteVacationPoint(id);
        return "redirect:/admin_panel";
    }

    @PostMapping("/approve")
    public String approveVacationPoint(@RequestParam Long id) {
        adminService.updateApprovalStatus(id, true);
        return "redirect:/admin_panel";
    }


    @PostMapping("/submit")
    public String submitVacationPoint(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(required = false) MultipartFile[] photos,
            @RequestParam(required = false) String route
    ) {
        vacationService.saveVacationPoint(title, description, latitude, longitude, photos, route);
        return "redirect:/";
    }



    @GetMapping("/vacation_points")
    @ResponseBody
    public List<VacationPoint> getVacationPoints() {
        return adminService.getAllApprovedVacationPoints();
    }
}