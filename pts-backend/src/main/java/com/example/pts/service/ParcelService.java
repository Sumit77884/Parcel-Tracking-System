package com.example.pts.service;

import com.example.pts.dto.CreateParcelDTO;
import com.example.pts.entity.Parcel;
import com.example.pts.entity.ParcelStatus;
import com.example.pts.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository repo;

    public ParcelService(ParcelRepository repo) {
        this.repo = repo;
    }

    public Parcel register(CreateParcelDTO dto) {
        Parcel p = new Parcel();
        p.setSenderId(dto.senderId);
        p.setReceiverName(dto.receiverName);
        p.setReceiverPhone(dto.receiverPhone);
        p.setReceiverAddress(dto.receiverAddress);
        p.setWeightKg(dto.weightKg);
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        p.setStatus(ParcelStatus.REGISTERED);
        p.setTrackingNumber("PTS-" + UUID.randomUUID().toString().substring(0,8).toUpperCase());
        return repo.save(p);
    }

    public Optional<Parcel> findByTracking(String tracking) {
        return repo.findByTrackingNumber(tracking);
    }

    /**
     * Save/update a parcel. Use this to persist status changes or other updates.
     */
    public Parcel save(Parcel parcel) {
        parcel.setUpdatedAt(LocalDateTime.now());
        return repo.save(parcel);
    }
}
