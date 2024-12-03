package dci.j24e01.TravelBlog.service;

import dci.j24e01.TravelBlog.models.Photo;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.repositories.PhotoRepository;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private VacationPointRepository vacationPointRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private GeocodingService geocodingService;


    public List<VacationPoint> getAllApprovedVacationPoints() {
        return vacationPointRepository.findAllByApprovedTrue();  // Этот метод будет использоваться для фильтрации
    }

    public List<VacationPoint> getAllVacationPoints() {
        return vacationPointRepository.findAll();
    }

    public void saveVacationPoint(String city, String country, String description, LocalDateTime startDate, LocalDateTime endDate, MultipartFile[] photos) {
        double[] coordinates = geocodingService.getCoordinates(city, country);

        VacationPoint vacationPoint = new VacationPoint();
        vacationPoint.setCity(city);
        vacationPoint.setCountry(country);
        vacationPoint.setDescription(description);
        vacationPoint.setStartDate(startDate);
        vacationPoint.setEndDate(endDate);
        vacationPoint.setLatitude(coordinates[0]);
        vacationPoint.setLongitude(coordinates[1]);
        vacationPoint.setApproved(true);

        VacationPoint savedPoint = vacationPointRepository.save(vacationPoint);

        if (photos != null) {
            List<Photo> photoList = new ArrayList<>();
            for (MultipartFile photo : photos) {
                try {
                    String filename = UUID.randomUUID() + "." + photo.getOriginalFilename().split("\\.")[1];
                    Path destination = Path.of("src/main/resources/static/photos", filename);
                    photo.transferTo(destination);

                    Photo photoEntity = new Photo();
                    photoEntity.setPhotoPath(filename);
                    photoEntity.setVacationPoint(savedPoint);
                    photoList.add(photoEntity);

                    String uploadsDir = "src/main/resources/static/photos/";
                    Path uploadsPath = Path.of(uploadsDir);
                    System.out.println("Photos directory: " + uploadsPath.toAbsolutePath());
                } catch (Exception e) {
                    throw new RuntimeException("Error saving photo", e);
                }
            }
            photoRepository.saveAll(photoList);
        }
    }
    public void deleteVacationPoint(Long id) {
        vacationPointRepository.deleteById(id);
    }



    public void updateApprovalStatus(Long id, boolean approved) {
        VacationPoint vacationPoint = vacationPointRepository.findById(id).orElseThrow();
        vacationPoint.setApproved(approved);
        vacationPointRepository.save(vacationPoint);
    }

}
