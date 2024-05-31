package com.example.modelbase.service;

import com.example.modelbase.dto.response.FollowerResponseDto;
import com.example.modelbase.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowersService
{
    private final UserService userService;

    public List<FollowerResponseDto> getFollowers(String token) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        List<User> followed = List.copyOf(currentUser.getFollows());
        List<FollowerResponseDto> response = new LinkedList<>();

        for(User follow: followed)
        {
            FollowerResponseDto followerDto = new FollowerResponseDto();
            followerDto.setFollowerId(follow.getId());
            followerDto.setUsername(follow.getUsername());
            followerDto.setDescription(follow.getDescription());
            response.add(followerDto);
        }
        return response;
    }

}
