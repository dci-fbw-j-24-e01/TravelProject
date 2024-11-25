package dci.j24e01.TravelBlog.controllers;


import dci.j24e01.TravelBlog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("vacationPoints", adminService.getAllVacationPoints());
        return "admin";
    }


    @PostMapping("/delete")
    public String deleteVacationPoint(@RequestParam Long id) {
        adminService.deleteVacationPoint(id);
        return "redirect:/admin";
    }
}
