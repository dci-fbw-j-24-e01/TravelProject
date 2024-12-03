package dci.j24e01.TravelBlog.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VacationPointDTO {
    private String city;
    private String country;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> photos;

    public VacationPointDTO() {
    }

    public VacationPointDTO(String city, String country, String description, double latitude, double longitude, LocalDateTime startDate, LocalDateTime endDate, List<String> photos) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photos = photos;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VacationPointDTO that)) return false;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, description, latitude, longitude, startDate, endDate, photos);
    }

    @Override
    public String toString() {
        return "VacationPointDTO{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", photos=" + photos +
                '}';
    }


}
