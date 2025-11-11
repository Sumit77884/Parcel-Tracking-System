package com.example.pts.controller;

import com.example.pts.dto.CreateParcelDTO;
import com.example.pts.dto.ParcelDTO;
import com.example.pts.entity.Parcel;
import com.example.pts.entity.ParcelStatus;
import com.example.pts.service.ParcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parcels")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParcelDTO> register(@RequestBody CreateParcelDTO dto) {
        Parcel saved = service.register(dto);
        return ResponseEntity.ok(ParcelDTO.fromEntity(saved));
    }

    @GetMapping("/{tracking}")
    public ResponseEntity<?> get(@PathVariable String tracking) {
        var opt = service.findByTracking(tracking);
        if (opt.isPresent()) {
            Parcel p = opt.get();
            ParcelDTO dto = ParcelDTO.fromEntity(p);
            return ResponseEntity.ok(dto);
        } else {
            Map<String, String> err = Map.of("error", "Parcel not found");
            return ResponseEntity.status(404).body(err);
        }
    }

    @PostMapping("/{tracking}/events")
    public ResponseEntity<?> addEvent(@PathVariable String tracking, @RequestBody Map<String,Object> body) {
        Map<String,Object> resp = new HashMap<>();
        resp.put("message","event recorded (placeholder)");
        resp.put("tracking", tracking);
        resp.put("event", body);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{tracking}/confirm-delivery")
    public ResponseEntity<?> confirmDelivery(@PathVariable String tracking, @RequestBody Map<String,Object> body) {
        return service.findByTracking(tracking).map(p -> {
            p.setStatus(ParcelStatus.DELIVERED);
            service.save(p); // persist status change
            Map<String,Object> r = new HashMap<>();
            r.put("message","delivered");
            r.put("tracking", tracking);
            r.put("body", body);
            return ResponseEntity.ok(r);
        }).orElseGet(() -> ResponseEntity.status(404).body(Map.of("error","Parcel not found")));
    }
}