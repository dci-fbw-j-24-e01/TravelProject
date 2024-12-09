package dci.j24e01.TravelBlog.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "detailData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_point_id", referencedColumnName = "id")
    private VacationPoint vacationPoint;


    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DetailData() {

    }

    public DetailData(Long id, String cityName, String countryName, LocalDate startDate, LocalDate endDate, String description, double latitude, double longitude, List<Photo> photos, VacationPoint vacationPoint, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.cityName = cityName;
        this.countryName = countryName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photos = photos;
        this.vacationPoint = vacationPoint;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Photo> getPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VacationPoint getVacationPoint() {
        return vacationPoint;
    }

    public void setVacationPoint(VacationPoint vacationPoint) {
        this.vacationPoint = vacationPoint;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetailData that = (DetailData) o;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && Objects.equals(id, that.id) && Objects.equals(cityName, that.cityName) && Objects.equals(countryName, that.countryName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description) && Objects.equals(photos, that.photos) && Objects.equals(vacationPoint, that.vacationPoint) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName, countryName, startDate, endDate, description, latitude, longitude, photos, vacationPoint, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "DetailData{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", photos=" + photos +
                ", vacationPoint=" + vacationPoint +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
