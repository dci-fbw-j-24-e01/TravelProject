package dci.j24e01.TravelBlog.controllers;

import dci.j24e01.TravelBlog.models.HeroSettings;
import dci.j24e01.TravelBlog.models.Location;
import dci.j24e01.TravelBlog.models.PendingLocation;
import dci.j24e01.TravelBlog.repositories.HeroSettingsRepository;
import dci.j24e01.TravelBlog.repositories.LocationRepository;
import dci.j24e01.TravelBlog.repositories.PendingLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin_panel")
public class AdminController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PendingLocationRepository pendingLocationRepository;

    @Autowired
    private HeroSettingsRepository heroSettingsRepository;

    // Display Admin Panel
    @GetMapping
    public String adminPanel(Model model) {
        // Fetch hero settings (one row expected)
        HeroSettings heroSettings = heroSettingsRepository.findTopByOrderByIdDesc();
        List<Location> locations = locationRepository.findAll();
        List<PendingLocation> pendingLocations = pendingLocationRepository.findAll();

        model.addAttribute("heroSettings", heroSettings);
        model.addAttribute("locations", locations);
        model.addAttribute("pendingLocations", pendingLocations);
        return "admin_panel";
    }

    @GetMapping("/hero_settings")
    public String getHeroSettings(Model model) {
        HeroSettings heroSettings = heroSettingsRepository.findAll().stream().findFirst().orElse(null); // Or retrieve it based on ID if needed
        model.addAttribute("heroSettings", heroSettings);
        return "admin_panel"; // The name of the admin panel view
    }

/*    // Handle saving Hero Settings
    @PostMapping("/hero_settings")
    public String saveHeroSettings(@RequestParam("background_image_url") String backgroundImageUrl,
                                   @RequestParam("title") String title) {
        // Check if hero settings exist
        HeroSettings heroSettings = heroSettingsRepository.findTopByOrderByIdDesc();

        if (heroSettings == null) {
            heroSettings = new HeroSettings();
        }

        heroSettings.setBackgroundImageUrl(backgroundImageUrl);
        heroSettings.setTitle(title);
        heroSettings.setUpdatedAt(LocalDateTime.now());

        heroSettingsRepository.save(heroSettings);

        return "redirect:/admin_panel"; // Redirect back to the admin panel
    }*/

    @PostMapping("/hero_settings")
    public String saveHeroSettings(@RequestParam("background_image_url") String backgroundImageUrl,
                                   @RequestParam("title") String title, Model model) {

        HeroSettings heroSettings = heroSettingsRepository.findAll().stream().findFirst().orElse(new HeroSettings());


        heroSettings.setBackgroundImageUrl(backgroundImageUrl);
        heroSettings.setTitle(title);


        heroSettingsRepository.save(heroSettings);


        model.addAttribute("heroSettings", heroSettings);


        return "admin_panel";
    }

}

// Other mappings for approving, rejecting, editing locations


