package com.example.modelbase.repository;

import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.Collection;
import com.example.modelbase.model.ModelKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectibleRepository extends JpaRepository<Collectible, Integer>
{
    Optional<Collectible> findByCollectionAndModelKit(Collection collection, ModelKit modelKit);
}