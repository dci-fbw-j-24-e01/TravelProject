package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.services.DetailDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DetailDataController {

    private final DetailDataService detailDataService;

    @Autowired
    public DetailDataController(DetailDataService detailDataService) {
        this.detailDataService = detailDataService;
    }

    @GetMapping("/detail")
    public String getDetailMockup(Model model) {
        return "detail";
    }

    @GetMapping("/detail/{id}")
    public String getDetailById(@PathVariable Long id, Model model) {
        DetailData detailData = detailDataService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail not found"));

        model.addAttribute("detailData", detailData);
        return "detail";
    }

}
