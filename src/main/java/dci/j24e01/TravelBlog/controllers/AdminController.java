//package dci.j24e01.TravelBlog.controllers;
//
//
//import dci.j24e01.TravelBlog.service.AdminService;
//import dci.j24e01.TravelBlog.service.VacationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("/admin_panel")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private VacationService vacationService;
//
//    @GetMapping
//    public String adminPage(Model model) {
//        model.addAttribute("vacationPoints", adminService.getAllVacationPoints());
//        return "admin_panel";
//    }
//
//
//    @PostMapping("/delete")
//    public String deleteVacationPoint(@RequestParam Long id) {
//        adminService.deleteVacationPoint(id);
//        return "redirect:/admin_panel";
//    }
//
//    @PostMapping("/approve")
//    public String approveVacationPoint(@RequestParam Long id) {
//        vacationService.updateApprovalStatus(id, true);
//        return "redirect:/admin_panel";
//    }
//}
