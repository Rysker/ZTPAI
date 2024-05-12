package com.example.modelbase.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collectible", schema = "public")
public class Collectible
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collectible_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_kit_id", nullable = false)
    private ModelKit modelKit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "progress_id", nullable = false)
    private Progress progress;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "list_order")
    private Integer listOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

}