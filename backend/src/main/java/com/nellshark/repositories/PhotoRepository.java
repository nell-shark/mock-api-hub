package com.nellshark.repositories;

import com.nellshark.models.Photo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoRepository extends AbstractGenericRepository<Photo> {

  public PhotoRepository() {
    super(List.of());
  }
}
