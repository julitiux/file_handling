package com.file_handling.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class FileDownloadController {

  @RequestMapping(value = "/download", method = RequestMethod.GET)
  public ResponseEntity<Object> donwloadFile() throws IOException {
    String filename = "OSXLion.webp";
    File file = new File(filename);
    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add("Content-Disposition", String.format("attachemnt; filename=\"%s\"", file.getName()));
    httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
    httpHeaders.add("Pragma", "no-cache");
    httpHeaders.add("Expires", "0");

    ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(httpHeaders).contentLength(
      file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
    return responseEntity;
  }

}
