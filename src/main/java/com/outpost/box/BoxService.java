package com.outpost.box;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository repository;

    public List<Box> getAllBox(){
        return repository.findAll();
    }

    public void addBox(Box box) {
        repository.save(box);
    }

    public Box getBoxById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found for id :: "  + id));
    }

    public void deleteBoxById(UUID id) {
        repository.deleteById(id);
    }

}
