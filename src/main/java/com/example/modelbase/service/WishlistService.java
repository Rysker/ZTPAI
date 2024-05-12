package com.example.modelbase.service;

import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.User;
import com.example.modelbase.model.Wishlist;
import com.example.modelbase.repository.ModelKitRepository;
import com.example.modelbase.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService
{
    private final WishlistRepository wishlistRepository;
    private final ModelKitRepository modelKitRepository;
    private final UserService userService;

    public void changeWishlist(String token, Integer modelId)
    {
        User user = userService.getUserFromToken(token);
        ModelKit modelKit = modelKitRepository.findById(modelId.intValue());
        Optional<Wishlist> wishlistItem = wishlistRepository.findByUserAndModelKit(user, modelKit);

        if (!wishlistItem.isPresent())
        {
            Wishlist newWishlistItem = new Wishlist();
            newWishlistItem.setUser(user);
            newWishlistItem.setModelKit(modelKit);
            wishlistRepository.save(newWishlistItem);
        }
        else
            wishlistRepository.delete(wishlistItem.get());
    }
}
