package com.outpost.parcelLocker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelLockerService {

    private final ParcelLockerRepository repository;

    public List<ParcelLocker> getAllParcelLocker() {
        return repository.findAll();
    }

    public void addParcelLocker(ParcelLocker parcelLocker) {
        repository.save(parcelLocker);
    }

    public ParcelLocker getParcelLockerById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found for id :: "  + id));
    }

    public void deleteParcelLockerById(String id) {
        repository.deleteById(id);
    }

}


