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
        Optional<ModelKit> modelKit = modelKitRepository.findById(modelId.intValue());
        if(modelKit.isPresent())
        {
            Optional<Wishlist> wishlistItem = wishlistRepository.findByUserAndModelKit(user, modelKit.get());

            if (wishlistItem.isEmpty())
            {
                Wishlist newWishlistItem = new Wishlist();
                newWishlistItem.setUser(user);
                newWishlistItem.setModelKit(modelKit.get());
                wishlistRepository.save(newWishlistItem);
            }
            else
                wishlistRepository.delete(wishlistItem.get());
        }
    }
}
