package com.outpost.service;

import com.outpost.domain.ParcelLocker;
import com.outpost.repository.ParcelLockerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<ParcelLocker> optional = repository.findById(id);
        ParcelLocker parcelLocker;
        if (optional.isPresent()) {
            parcelLocker = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return parcelLocker;
    }

    public void deleteParcelLockerById(String id) {
        repository.deleteById(id);
    }

}


