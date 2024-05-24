package com.example.travelitineraryapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String details;
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    @JsonBackReference("itinerary-booking")
    private Itinerary itinerary;
}
