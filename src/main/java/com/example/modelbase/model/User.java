package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"", schema = "public", indexes = {
        @Index(name = "user_email_email1_key", columnList = "email", unique = true)
})
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "username", nullable = false, length = 40)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "created", nullable = false)
    private Date created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "collection",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id")
    )
    private Collection collection;

    @OneToMany(mappedBy = "user")
    private Set<Like> likes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Report> reports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "model_kit_id")
    )
    private Set<ModelKit> modelKits = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "follower_list",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private Set<User> follows = new LinkedHashSet<>();

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(accountType.getName()));
    }

    public String dateToString()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        return formatter.format(this.created);
    }

}