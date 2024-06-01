package com.example.modelbase.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AvatarChangeDto
{
    private MultipartFile avatar;
}
