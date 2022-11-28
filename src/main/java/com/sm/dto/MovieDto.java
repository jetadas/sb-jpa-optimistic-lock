package com.sm.dto;

import java.io.Serializable;


public class MovieDto implements Serializable {

    private Long id;
    private Integer version;
    private String title;
    private Integer rating;

    public Long getId() {
        return id;
    }

    public MovieDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public MovieDto setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public MovieDto setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
