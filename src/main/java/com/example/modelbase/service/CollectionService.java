package com.example.modelbase.service;

import com.example.modelbase.dto.response.CollectionResponseDto;
import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.mapper.ModelKitMapper;
import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.Collection;
import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CollectionService
{
    private final CollectibleRepository collectibleRepository;
    private final ModelKitRepository modelKitRepository;
    private final CollectionRepository collectionRepository;
    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final ModelKitMapper modelKitMapper;
    private final UserService userService;


    public void addToCollection(String token, Integer modelId) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<ModelKit> modelKit = modelKitRepository.findById(modelId);
        if(modelKit.isPresent() && collectibleRepository.findByCollectionAndModelKit(currentUser.getCollection(), modelKit.get()).isEmpty())
        {
            Collectible newCollectible = new Collectible();
            newCollectible.setCollection(currentUser.getCollection());
            newCollectible.setModelKit(modelKit.get());
            newCollectible.setIsPublic(true);
            newCollectible.setProgress(progressRepository.findProgressByName("Owned"));
            newCollectible.setCompletionDate(null);
            newCollectible.setListOrder(currentUser.getCollection().getCollectibles().size() + 1);
            collectibleRepository.save(newCollectible);
        }
        else
            throw new IllegalArgumentException("Model doesn't exist!");
    }

    public CollectionResponseDto getUserCollection(String token, String username) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<User> targetUser = userRepository.findUserByUsername(username);
        if(targetUser.isPresent())
        {
            User target = targetUser.get();
            List<ModelKitDto> kitsDto = new ArrayList<>();
            List<Collectible> collectibles = new ArrayList<>(target.getCollection().getCollectibles());
            collectibles.sort(Comparator.comparing(Collectible::getListOrder));
            Boolean isSameUser = currentUser.getUsername().equals(target.getUsername());

            CollectionResponseDto response = new CollectionResponseDto();
            response.setIsSameUser(isSameUser.toString());

            for(Collectible collectible: collectibles)
            {
                if(collectible.getIsPublic() || isSameUser)
                {
                    ModelKit kit = collectible.getModelKit();
                    ModelKitDto kitDto = modelKitMapper.userCollectionMap(username, kit);
                    kitsDto.add(kitDto);
                    if(!isSameUser)
                        kitDto.setOnWatchlist(false);
                }
            }
            response.setKits(kitsDto);
            return response;
        }
        else
            throw new IllegalArgumentException("User does not exist!");
    }

    public void changeVisibility(String token, Integer collectibleId) throws Exception
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.getCollectibleById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            Collectible collectible = optionalCollectible.get();
            collectible.setIsPublic(!collectible.getIsPublic());
            collectibleRepository.save(collectible);
        }
        else
            throw new IllegalArgumentException("Collectible does not exist!");
    }

    public void reshuffle(Collection collection, Integer border) throws Exception
    {
        ArrayList<Collectible> res = new ArrayList<>(collection.getCollectibles());
        res.removeIf(x -> x.getListOrder().equals(border));
        for(Collectible tmp: res)
        {
            if(tmp.getListOrder() > border)
                tmp.setListOrder(tmp.getListOrder() - 1);
        }

        Set<Collectible> x = new HashSet<>(res);

        collection.setCollectibles(x);
        collectionRepository.save(collection);
    }
}
