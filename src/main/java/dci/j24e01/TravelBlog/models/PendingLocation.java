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

    // Getters and Setters
}