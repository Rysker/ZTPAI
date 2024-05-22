package com.example.modelbase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FollowerListId implements Serializable
{
    private static final long serialVersionUID = 570586229949044113L;
    @Column(name = "follower_id", nullable = false)
    private Integer followerId;

    @Column(name = "followed_id", nullable = false)
    private Integer followedId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FollowerListId entity = (FollowerListId) o;
        return Objects.equals(this.followerId, entity.followerId) &&
                Objects.equals(this.followedId, entity.followedId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(followerId, followedId);
    }

}