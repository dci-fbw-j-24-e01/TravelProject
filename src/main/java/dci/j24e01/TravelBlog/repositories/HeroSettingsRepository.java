package dci.j24e01.TravelBlog.repositories;

import dci.j24e01.TravelBlog.models.HeroSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroSettingsRepository extends JpaRepository<HeroSettings, Long> {
    HeroSettings findTopByOrderByIdDesc(); // Fetch the latest hero settings (assuming only one row is present)
}