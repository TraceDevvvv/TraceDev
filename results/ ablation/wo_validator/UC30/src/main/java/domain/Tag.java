package domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a Tag in the domain.
 */
public class Tag {
    private String id;
    private String name;
    private String description;
    private Date createdAt;

    public Tag(String id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Validates if the tag has required fields.
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
               description != null && !description.trim().isEmpty();
    }

    /**
     * Compares this tag with another based on name and description.
     */
    public boolean equals(Tag otherTag) {
        if (this == otherTag) return true;
        if (otherTag == null) return false;
        return Objects.equals(name, otherTag.name) &&
               Objects.equals(description, otherTag.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return equals(tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}