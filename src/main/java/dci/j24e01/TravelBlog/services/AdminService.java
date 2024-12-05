package dci.j24e01.TravelBlog.services;

import dci.j24e01.TravelBlog.models.Photo;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.repositories.PhotoRepository;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
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

    public void saveVacationPoint(String city, String country, String description, LocalDate startDate, LocalDate endDate, MultipartFile[] photos) {
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

                    Resource staticResource = new ClassPathResource("static");
                    Path targetUploadsPath = Path.of(staticResource.getURI()).resolve("photos");
                    Path targetDestination = targetUploadsPath.resolve(filename);
                    photo.transferTo(targetDestination);
                } catch (Exception e) {
                    throw new RuntimeException("Error saving photo", e);
                }
            }
            photoRepository.saveAll(photoList);
        }
    }

    public void saveVacationPoint(Long id, String city, String country, String description, LocalDate startDate, LocalDate endDate, MultipartFile[] photos) {
        double[] coordinates = geocodingService.getCoordinates(city, country);

        VacationPoint vacationPoint = findVacationPointById(id);
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
                if (photo == null || photo.isEmpty()) {
                    continue;
                }
                try {

                    String originalFilename = photo.getOriginalFilename();
                    String extension = originalFilename != null && originalFilename.contains(".")
                            ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1)
                            : "";

                    String filename = UUID.randomUUID() + "." + extension;

                    Path destination = Path.of("src/main/resources/static/photos", filename);
                    photo.transferTo(destination);

                    Photo photoEntity = new Photo();
                    photoEntity.setPhotoPath(filename);
                    photoEntity.setVacationPoint(savedPoint);
                    photoList.add(photoEntity);

                    Resource staticResource = new ClassPathResource("static");
                    Path targetUploadsPath = Path.of(staticResource.getURI()).resolve("photos");
                    Path targetDestination = targetUploadsPath.resolve(filename);
                    photo.transferTo(targetDestination);

                } catch (Exception e) {
                    throw new RuntimeException("Error saving photo", e);
                }
            }
//            photoRepository.deleteAllByVacationPointId(savedPoint.getId());
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

    public VacationPoint findVacationPointById(Long id) {
        return vacationPointRepository.findById(id).orElse(null);
    }

}
