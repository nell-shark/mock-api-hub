package com.nellshark.services;

import com.nellshark.models.Photo;
import com.nellshark.repositories.PhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoService extends AbstractGenericService<Photo> {

  public PhotoService(PhotoRepository photoRepository) {
    super(photoRepository);
  }
}
