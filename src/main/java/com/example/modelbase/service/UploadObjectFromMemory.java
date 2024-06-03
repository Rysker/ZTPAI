package com.example.modelbase.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadObjectFromMemory
{
  @Value("${google.bucket.name}")
  String bucketName;
  private final Storage storage;
  @PostConstruct
  public void init()
  {
      warmUp();
  }

  public void uploadImageToGCS(String fileName, byte[] imageData)
  {

      BlobId blobId = BlobId.of(bucketName, fileName);
      BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
      storage.create(blobInfo, imageData);
  }

  public void removeImageFromGCS(String fileName)
  {
      BlobId blobId = BlobId.of(bucketName, fileName);
      boolean deleted = storage.delete(blobId);
      if (!deleted)
          throw new RuntimeException("Failed to delete file");
  }

    private void warmUp()
    {
        try
        {
            BlobId blobId = BlobId.of(bucketName, "warm-up");
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, new byte[0]);
            storage.delete(blobId);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Google Cloud Storage error!");
        }
    }
}