package com.example.modelbase.service;

import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.Progress;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.CollectibleRepository;
import com.example.modelbase.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollectibleService
{
    private final CollectibleRepository collectibleRepository;
    private final UserService userService;
    private final CollectionService collectionService;
    private final ProgressRepository progressRepository;

    public void deleteCollectible(String token, Integer collectibleId) throws Exception
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.getCollectibleById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            Integer deletedOrder = optionalCollectible.get().getListOrder();
            collectibleRepository.delete(optionalCollectible.get());
            if(user.getCollection().getCollectibles().size() - 1 > 0)
                collectionService.reshuffle(user.getCollection(), deletedOrder);
        }
        else
            throw new IllegalArgumentException("No collectible found!");
    }

    public void changeProgress(String token, Integer collectibleId)
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.getCollectibleById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            Collectible collectible = optionalCollectible.get();
            Progress progress = progressRepository.findProgressByName("Finished");
            collectible.setProgress(progress);
            collectible.setCompletionDate(LocalDate.now());
            collectibleRepository.save(collectible);
        }
        else
            throw new IllegalArgumentException("No collectible found!");
    }

    public void changeDate(String token, Integer collectibleId, LocalDate date) throws Exception
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.getCollectibleById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            LocalDate localToday = LocalDate.now().plusDays(1);
            if(date.isBefore(localToday))
            {
                optionalCollectible.get().setCompletionDate(date);
                collectibleRepository.save(optionalCollectible.get());
            }
            else
                throw new IllegalArgumentException("Can't set future date!");
        }
        else
            throw new IllegalArgumentException("No collectible found!");
    }

}
