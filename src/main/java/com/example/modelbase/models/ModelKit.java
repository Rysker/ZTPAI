package com.example.modelbase.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "model_kit", schema = "public")
public class ModelKit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_kit_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "use_country_id", nullable = false)
    private Country useCountry;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "manufacturer_code", nullable = false, length = 100)
    private String manufacturerCode;

    @OneToMany(mappedBy = "modelKit")
    private Set<EavTable> eavTables = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modelKit")
    private Set<Photo> photos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modelKit")
    private Set<Review> reviews = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Country getUseCountry() {
        return useCountry;
    }

    public void setUseCountry(Country useCountry) {
        this.useCountry = useCountry;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public Set<EavTable> getEavTables() {
        return eavTables;
    }

    public void setEavTables(Set<EavTable> eavTables) {
        this.eavTables = eavTables;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}