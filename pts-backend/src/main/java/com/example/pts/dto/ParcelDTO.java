package com.example.pts.dto;

import com.example.pts.entity.Parcel;
import com.example.pts.entity.ParcelStatus;

public class ParcelDTO {
    public String trackingNumber;
    public String receiverName;
    public String receiverPhone;
    public String receiverAddress;
    public Double weightKg;
    public ParcelStatus status;

    public static ParcelDTO fromEntity(Parcel p) {
        ParcelDTO d = new ParcelDTO();
        d.trackingNumber = p.getTrackingNumber();
        d.receiverName = p.getReceiverName();
        d.receiverPhone = p.getReceiverPhone();
        d.receiverAddress = p.getReceiverAddress();
        d.weightKg = p.getWeightKg();
        d.status = p.getStatus();
        return d;
    }
}
