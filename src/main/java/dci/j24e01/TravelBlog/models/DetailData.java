package dci.j24e01.TravelBlog.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class DetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String countryName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


    @ElementCollection
    private List<String> imageUrls;



}
