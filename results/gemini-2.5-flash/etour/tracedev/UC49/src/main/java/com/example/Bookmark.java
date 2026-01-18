package com.example;

import java.util.Objects;

/**
 * Represents a single bookmark with an ID, name, and URL.
 * This class is immutable once created.
 */
public class Bookmark {
    private String id;
    private String name;
    private String url;

    /**
     * Constructs a new Bookmark.
     * @param id The unique identifier for the bookmark.
     * @param name The display name of the bookmark.
     * @param url The URL associated with the bookmark.
     */
    public Bookmark(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    /**
     * Gets the ID of the bookmark.
     * @return The bookmark's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the bookmark.
     * @return The bookmark's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the URL of the bookmark.
     * @return The bookmark's URL.
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", url='" + url + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(id, bookmark.id) &&
               Objects.equals(name, bookmark.name) &&
               Objects.equals(url, bookmark.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}