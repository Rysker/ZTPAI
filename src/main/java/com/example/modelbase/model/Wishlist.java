package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist
{
    @EmbeddedId
    private WishlistId id = new WishlistId();

    @MapsId("modelKitId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "model_kit_id", nullable = false)
    private ModelKit modelKit;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}