package com.outpost.parcelLocker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelLockerRepository extends JpaRepository<ParcelLocker, String> {

}
