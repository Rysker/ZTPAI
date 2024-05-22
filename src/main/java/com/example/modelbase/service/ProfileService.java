package com.example.modelbase.service;

import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.dto.response.ProfileResponseDto;
import com.example.modelbase.mapper.ProfileMapper;
import com.example.modelbase.model.FollowerList;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.FollowerListRepository;
import com.example.modelbase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService
{
    private final ProfileMapper profileMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FollowerListRepository followerListRepository;

    public ProfileResponseDto getProfile(String token, String username) throws Exception
    {
        return profileMapper.mapProfile(token, username);
    }

    public void changeFollow(String token, String username) throws Exception
    {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        User currentUser = userService.getUserFromToken(token);
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            Optional<FollowerList> followerList = followerListRepository.getByFollowerAndFollowed(currentUser, user);
            if(followerList.isPresent())
                followerListRepository.delete(followerList.get());
            else
            {
                FollowerList newEntry = new FollowerList();
                newEntry.setFollower(currentUser);
                newEntry.setFollowed(user);
                followerListRepository.save(newEntry);
            }
        }
        else
            throw new IllegalArgumentException("User does not exist!");
    }
}
