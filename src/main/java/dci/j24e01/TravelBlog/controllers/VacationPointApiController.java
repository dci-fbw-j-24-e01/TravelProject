package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacation_points")
public class VacationPointApiController {
    @Autowired
    private VacationPointRepository vacationPointRepository;

    @GetMapping
    public List<VacationPoint> getAllVacationPoints() {
        return vacationPointRepository.findAllByApprovedTrue();
    }
}