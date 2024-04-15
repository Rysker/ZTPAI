package com.example.modelbase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "photo", schema = "public")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_kit_id", nullable = false)
    private ModelKit modelKit;

    @Column(name = "image", nullable = false, length = 200)
    private String image;

    @Column(name = "\"isMain\"", nullable = false)
    private Boolean isMain = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelKit getModelKit() {
        return modelKit;
    }

    public void setModelKit(ModelKit modelKit) {
        this.modelKit = modelKit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

}