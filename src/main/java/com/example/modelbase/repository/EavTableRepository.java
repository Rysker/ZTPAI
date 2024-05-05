package com.example.modelbase.repository;

import com.example.modelbase.model.EavTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EavTableRepository extends JpaRepository<EavTable, Integer>
{
    Optional<EavTable> findByModelKitIdAndAttributeName(Integer modelKitId, String attributeName);
}
