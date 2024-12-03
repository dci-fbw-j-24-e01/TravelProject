package dci.j24e01.TravelBlog.models;

import dci.j24e01.TravelBlog.models.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PendingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;
    private String country;
    private Date visitDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "suggested_by", referencedColumnName = "id")
    private User suggestedBy; // Assume a 'User' entity exists for the admin user

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date createdAt;
    private Date updatedAt;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSuggestedBy() {
        return suggestedBy;
    }

    public void setSuggestedBy(User suggestedBy) {
        this.suggestedBy = suggestedBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}