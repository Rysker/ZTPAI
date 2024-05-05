package com.example.modelbase.repository;

import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.ModelKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectibleRepository extends JpaRepository<Collectible, Integer>
{
    Collectible findByIdAndModelKit(Integer userId, ModelKit modelKit);
}