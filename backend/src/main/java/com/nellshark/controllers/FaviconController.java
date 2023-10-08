package com.nellshark.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaviconController {

  @GetMapping("favicon.ico")
  public ResponseEntity<Void> returnNoFavicon() {
    return ResponseEntity.ok().build();
  }
}
