package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "model_kit", schema = "public")
public class ModelKit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_kit_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "use_country_id", nullable = false)
    private Country useCountry;

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

    public String getMainPhoto()
    {
        Set<Photo> photos = this.getPhotos();
        for(Photo photo: photos)
        {
            if(photo.getIs_main())
                return photo.getImage();
        }
        return "Error!";
    }

    public Set<Review> getReviews()
    {
        return this.reviews.stream().filter(review -> !review.getReviewStatus().getName().equals("BLOCKED")).collect(Collectors.toSet());

    }
}