package com.photoapp.photoapp.repository;

import com.photoapp.photoapp.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
