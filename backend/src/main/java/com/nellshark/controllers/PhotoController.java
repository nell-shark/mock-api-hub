package com.nellshark.controllers;

import com.nellshark.models.Photo;
import com.nellshark.services.PhotoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController extends AbstractGenericController<Photo> {

  public PhotoController(PhotoService service) {
    super(service);
  }
}
