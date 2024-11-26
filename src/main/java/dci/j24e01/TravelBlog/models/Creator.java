package dci.j24e01.TravelBlog.models;


import java.util.Objects;

//PROVISIONAL MODEL, HAS TO BE CONFIRMED LATER
public class Creator {
    private String name;
    private String role;
    private String bio;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Creator(String name, String role, String bio, String imageUrl) {
        this.name = name;
        this.role = role;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(name, creator.name) && Objects.equals(role, creator.role) && Objects.equals(bio, creator.bio) && Objects.equals(imageUrl, creator.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, bio, imageUrl);
    }

    @Override
    public String toString() {
        return "Creator{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", bio='" + bio + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

