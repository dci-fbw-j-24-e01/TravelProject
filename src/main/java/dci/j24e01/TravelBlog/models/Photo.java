package dci.j24e01.TravelBlog.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoPath;


    @ManyToOne
    @JoinColumn(name = "vacation_point_id", nullable = true)
    private VacationPoint vacationPoint;


    @ManyToOne
    @JoinColumn(name = "detail_data_id", nullable = true)
    private DetailData detailData;


    public Photo() {
    }


    public Photo(Long id, String photoPath, VacationPoint vacationPoint, DetailData detailData) {
        this.id = id;
        this.photoPath = photoPath;
        this.vacationPoint = vacationPoint;
        this.detailData = detailData;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public VacationPoint getVacationPoint() {
        return vacationPoint;
    }

    public void setVacationPoint(VacationPoint vacationPoint) {
        this.vacationPoint = vacationPoint;
    }

    public DetailData getDetailData() {
        return detailData;
    }

    public void setDetailData(DetailData detailData) {
        this.detailData = detailData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(photoPath, photo.photoPath) &&
                Objects.equals(vacationPoint, photo.vacationPoint) &&
                Objects.equals(detailData, photo.detailData);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, photoPath, vacationPoint, detailData);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", photoPath='" + photoPath + '\'' +
                ", vacationPoint=" + (vacationPoint != null ? vacationPoint.getId() : "null") +
                ", detailData=" + (detailData != null ? detailData.getId() : "null") +
                '}';
    }
}
