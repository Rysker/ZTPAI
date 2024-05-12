package com.example.modelbase.service;

import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.User;
import com.example.modelbase.model.Wishlist;
import com.example.modelbase.repository.CollectibleRepository;
import com.example.modelbase.repository.CollectionRepository;
import com.example.modelbase.repository.ModelKitRepository;
import com.example.modelbase.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollectionService
{
    private final CollectibleRepository collectibleRepository;
    private final CollectionRepository collectionRepository;
    private final ModelKitRepository modelKitRepository;
    private final UserService userService;
    private final ProgressRepository progressRepository;

    public void addToCollection(String token, Integer modelId)
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<ModelKit> modelKit = modelKitRepository.findById(modelId);
        if(modelKit.isPresent() && collectibleRepository.findByCollectionAndModelKit(currentUser.getCollection(), modelKit.get()).isEmpty())
        {
            Collectible newCollectible = new Collectible();
            newCollectible.setCollection(currentUser.getCollection());
            newCollectible.setModelKit(modelKit.get());
            newCollectible.setIsPublic(true);
            newCollectible.setProgress(progressRepository.findProgressByName("OWNED"));
            newCollectible.setCompletionDate(null);
            newCollectible.setListOrder(currentUser.getCollection().getCollectibles().size() + 1);
            collectibleRepository.save(newCollectible);
        }
    }

}
