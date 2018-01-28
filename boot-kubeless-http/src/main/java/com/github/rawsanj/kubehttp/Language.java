package com.github.rawsanj.kubehttp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Language {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Language cannot be null")
    private String language;

    @Max(value = 5, message = "Maximum Rating is 5")
    @Min(value = 1, message = "Minium Rating is 1")
    @NotNull(message = "Please rate the language.")
    private Float rate;

    public Language(String language, Float rate) {
        this.language = language;
        this.rate = rate;
    }

    public Language() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", rate=" + rate +
                '}';
    }
}
