package com.example.modelbase.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WishlistId implements Serializable {
    private static final long serialVersionUID = -582990601623953744L;
    @Column(name = "model_kit_id", nullable = false)
    private Integer modelKitId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getModelKitId() {
        return modelKitId;
    }

    public void setModelKitId(Integer modelKitId) {
        this.modelKitId = modelKitId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WishlistId entity = (WishlistId) o;
        return Objects.equals(this.modelKitId, entity.modelKitId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelKitId, userId);
    }

}