package com.photoapp.photoapp.web;

import com.photoapp.photoapp.model.Photo;
import com.photoapp.photoapp.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotoController {

    private final PhotosService photosService;

    public PhotoController(@Autowired PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable Integer id) {
        Photo photo = photosService.get(id);

        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/photos")
    public Photo create (@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
