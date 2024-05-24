package com.example.travelitineraryapi.service;

import com.example.travelitineraryapi.entity.Destination;
import com.example.travelitineraryapi.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }

    public Destination updateDestination(Long id, Destination destinationDetails) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            Destination destination = optionalDestination.get();
            destination.setName(destinationDetails.getName());
            destination.setDescription(destinationDetails.getDescription());
            destination.setLocation(destinationDetails.getLocation());
            destination.setBestTimeToVisit(destinationDetails.getBestTimeToVisit());
            return destinationRepository.save(destination);
        } else {
            throw new RuntimeException("Destination not found with id " + id);
        }
    }

    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

    public Optional<Destination> findById(Long id) {
        return destinationRepository.findById(id);
    }
}
