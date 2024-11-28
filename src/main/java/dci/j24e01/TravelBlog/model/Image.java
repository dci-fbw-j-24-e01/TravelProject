package dci.j24e01.TravelBlog.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_entry_id", nullable = false)
    private BlogEntry blogEntry;

    private String imageUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlogEntry getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(BlogEntry blogEntry) {
        this.blogEntry = blogEntry;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(blogEntry, image.blogEntry) && Objects.equals(imageUrl, image.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogEntry, imageUrl);
    }
}
