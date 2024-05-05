package com.example.modelbase.repository;

import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.modelbase.model.Wishlist;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>
{
    boolean existsByUserAndModelKit(User user, ModelKit modelKit);
}
