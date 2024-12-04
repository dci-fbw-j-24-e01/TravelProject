package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.Location;
import dci.j24e01.TravelBlog.models.PendingLocation;
import dci.j24e01.TravelBlog.repositories.LocationRepository;
import dci.j24e01.TravelBlog.repositories.PendingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PendingLocationRepository pendingLocationRepository;


    @GetMapping("/suggest-location")
    public String showLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "suggest_location";
    }


    @PostMapping("/suggest-location")
    public String submitLocation(@ModelAttribute Location location) {

        Location savedLocation = locationRepository.save(location);

        if (savedLocation != null) {
            // To create a new PendingLocation linked to the saved Location
            PendingLocation pendingLocation = new PendingLocation();
            pendingLocation.setLocation(savedLocation);
            pendingLocation.setStatus(PendingLocation.Status.PENDING); //This makes "PENDING" the default value
            pendingLocationRepository.save(pendingLocation);

            return "redirect:/suggest-location?success";
        } else {
            return "redirect:/suggest-location?error";
        }
    }
}