package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "review", schema = "public")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_kit_id", nullable = false)
    private ModelKit modelKit;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "content", nullable = false, length = 256)
    private String content;

    @Column(name = "write_date", nullable = false)
    private LocalDate writeDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_status_id", nullable = false)
    private ReviewStatus reviewStatus;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Like> likes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Report> reports = new LinkedHashSet<>();

    public String findReason()
    {
        List<Report> reports = List.copyOf(this.getReports());
        Map<String, Integer> count = new TreeMap<>();
        for(Report report: reports)
            count.merge(report.getReason().getReasonName(), 1, Integer::sum);

        return count.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Integer getLikeScore()
    {
        int downvotes = 0;
        for(Like like: this.getLikes())
        {
            if(!like.getIsLike())
                downvotes++;
        }
        return likes.size() - 2 * downvotes;
    }

}