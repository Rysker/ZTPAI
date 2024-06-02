package com.example.modelbase.service;

import com.example.modelbase.dto.request.AvatarChangeDto;
import com.example.modelbase.dto.request.DescriptionChangeDto;
import com.example.modelbase.dto.response.ProfileResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import com.example.modelbase.mapper.ProfileMapper;
import com.example.modelbase.model.FollowerList;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.FollowerListRepository;
import com.example.modelbase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService
{
    @Value("${google.const.header}")
    private String imageHeader;
    private final ProfileMapper profileMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FollowerListRepository followerListRepository;
    private final UploadObjectFromMemory uploadObjectFromMemory;

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

    public void changeDescription(String token, DescriptionChangeDto request) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        currentUser.setDescription(request.getDescription());
        userRepository.save(currentUser);
    }

    public String changeAvatar(String token, AvatarChangeDto request) throws Exception
    {
        MultipartFile imageFile = request.getAvatar();
        User currentUser = userService.getUserFromToken(token);

        String originalFileName = imageFile.getOriginalFilename();

        if (originalFileName == null || originalFileName.isEmpty())
            throw new RuntimeException("The file name is invalid.");

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

        if (!List.of(".jpg", ".jpeg", ".png").contains(fileExtension.toLowerCase()))
            throw new RuntimeException("The file must be a JPEG or PNG image.");

        String fileName = currentUser.getUsername() + "_" + System.currentTimeMillis() + fileExtension;

        try
        {
            byte[] image = imageFile.getBytes();

            if (imageFile.isEmpty())
                throw new RuntimeException("The file is empty.");

            if (!imageFile.getContentType().startsWith("image/"))
                throw new RuntimeException("The file is not an image.");

            if(currentUser.getAvatar() != null)
                uploadObjectFromMemory.removeImageFromGCS(currentUser.extractFileNameFromUrl());

            uploadObjectFromMemory.uploadImageToGCS(fileName, image);
            currentUser.setAvatar(imageHeader + fileName);
            userRepository.save(currentUser);
            return (imageHeader + fileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }
}
