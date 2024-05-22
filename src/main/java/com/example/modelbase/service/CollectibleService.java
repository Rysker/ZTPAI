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
    private final ProgressRepository progressRepository;

    public void changeVisibility(String token, Integer collectibleId) throws Exception
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.findById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            Collectible collectible = optionalCollectible.get();
            Boolean newStatus = !collectible.getIsPublic();
            collectible.setIsPublic(newStatus);
            collectibleRepository.save(collectible);
        }
        else
            throw new IllegalArgumentException("No collectible found!");
    }

    public void deleteCollectible(String token, Integer collectibleId)
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.findById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
            collectibleRepository.delete(optionalCollectible.get());
        else
            throw new IllegalArgumentException("No collectible found!");
    }

    public void changeProgress(String token, Integer collectibleId)
    {
        User user = userService.getUserFromToken(token);
        Optional<Collectible> optionalCollectible = collectibleRepository.findById(collectibleId);
        if(optionalCollectible.isPresent() && user.getCollection().getCollectibles().contains(optionalCollectible.get()))
        {
            Collectible collectible = optionalCollectible.get();
            Progress progress = progressRepository.findProgressByName("FINISHED");
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
        Optional<Collectible> optionalCollectible = collectibleRepository.findById(collectibleId);
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
