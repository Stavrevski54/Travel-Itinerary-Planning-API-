package com.example.travelitineraryapi.controller;

import com.example.travelitineraryapi.entity.Itinerary;
import com.example.travelitineraryapi.entity.Account;
import com.example.travelitineraryapi.entity.Destination;
import com.example.travelitineraryapi.service.ItineraryService;
import com.example.travelitineraryapi.service.AccountService;
import com.example.travelitineraryapi.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<Itinerary> getAllItineraries() {
        return itineraryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable Long id) {
        Optional<Itinerary> itinerary = itineraryService.findById(id);
        return itinerary.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"}, produces = "application/json")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        Optional<Account> accountOptional = accountService.findById(itinerary.getAccount().getId());
        Optional<Destination> destinationOptional = destinationService.findById(itinerary.getDestination().getId());

        if (accountOptional.isPresent() && destinationOptional.isPresent()) {
            itinerary.setAccount(accountOptional.get());
            itinerary.setDestination(destinationOptional.get());
            // Ensure bookings and activities are associated with the itinerary
            if (itinerary.getBookings() != null) {
                itinerary.getBookings().forEach(booking -> booking.setItinerary(itinerary));
            }
            if (itinerary.getActivities() != null) {
                itinerary.getActivities().forEach(activity -> activity.setItinerary(itinerary));
            }
            Itinerary savedItinerary = itineraryService.save(itinerary);
            return ResponseEntity.ok(savedItinerary);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "application/json;charset=UTF-8"}, produces = "application/json")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable Long id, @RequestBody Itinerary itineraryDetails) {
        Optional<Itinerary> itineraryOptional = itineraryService.findById(id);
        if (itineraryOptional.isPresent()) {
            Optional<Account> accountOptional = accountService.findById(itineraryDetails.getAccount().getId());
            Optional<Destination> destinationOptional = destinationService.findById(itineraryDetails.getDestination().getId());

            if (accountOptional.isPresent() && destinationOptional.isPresent()) {
                Itinerary updatedItinerary = itineraryOptional.get();
                updatedItinerary.setName(itineraryDetails.getName());
                updatedItinerary.setStartDate(itineraryDetails.getStartDate());
                updatedItinerary.setEndDate(itineraryDetails.getEndDate());
                updatedItinerary.setAccount(accountOptional.get());
                updatedItinerary.setDestination(destinationOptional.get());

                // Ensure bookings and activities are associated with the itinerary
                if (itineraryDetails.getBookings() != null) {
                    itineraryDetails.getBookings().forEach(booking -> booking.setItinerary(updatedItinerary));
                    updatedItinerary.setBookings(itineraryDetails.getBookings());
                }
                if (itineraryDetails.getActivities() != null) {
                    itineraryDetails.getActivities().forEach(activity -> activity.setItinerary(updatedItinerary));
                    updatedItinerary.setActivities(itineraryDetails.getActivities());
                }

                Itinerary savedItinerary = itineraryService.save(updatedItinerary);
                return ResponseEntity.ok(savedItinerary);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable Long id) {
        itineraryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
