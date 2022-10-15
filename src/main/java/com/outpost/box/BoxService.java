package com.outpost.box;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
