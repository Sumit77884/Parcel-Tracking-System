package com.example.pts.repository;

import com.example.pts.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    Optional<Parcel> findByTrackingNumber(String trackingNumber);
}
