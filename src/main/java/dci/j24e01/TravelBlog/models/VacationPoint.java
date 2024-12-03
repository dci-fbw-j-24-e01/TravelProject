package dci.j24e01.TravelBlog.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacation_points")
public class VacationPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private double latitude;
    private double longitude;

    private boolean approved;

    @OneToOne(mappedBy = "vacationPoint", cascade = CascadeType.ALL)
    private DetailData detailData;


    @OneToMany(mappedBy = "vacationPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    public VacationPoint() {
    }

    public VacationPoint(Long id, String city, String country, String description, LocalDateTime startDate, LocalDateTime endDate, double latitude, double longitude, List<Photo> photos, DetailData detailData, boolean approved) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photos = photos;
        this.detailData = detailData;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public DetailData getDetailData() {
        return detailData;
    }

    public void setDetailData(DetailData detailData) {
        this.detailData = detailData;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VacationPoint that)) return false;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && approved == that.approved && Objects.equals(id, that.id) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(detailData, that.detailData) && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, country, description, startDate, endDate, latitude, longitude, approved, detailData, photos);
    }

    @Override
    public String toString() {
        return "VacationPoint{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", approved=" + approved +
                ", detailData=" + detailData +
                ", photos=" + photos +
                '}';
    }
}

