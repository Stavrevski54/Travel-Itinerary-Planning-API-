package com.example.travelitineraryapi.service;

import com.example.travelitineraryapi.entity.Itinerary;
import com.example.travelitineraryapi.repository.ItineraryRepository;
import com.example.travelitineraryapi.entity.Account;
import com.example.travelitineraryapi.entity.Destination;
import com.example.travelitineraryapi.repository.AccountRepository;
import com.example.travelitineraryapi.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Itinerary> findAll() {
        return itineraryRepository.findAll();
    }

    public Optional<Itinerary> findById(Long id) {
        return itineraryRepository.findById(id);
    }

    public Itinerary save(Itinerary itinerary) {
        Optional<Account> account = accountRepository.findById(itinerary.getAccount().getId());
        Optional<Destination> destination = destinationRepository.findById(itinerary.getDestination().getId());

        account.ifPresent(itinerary::setAccount);
        destination.ifPresent(itinerary::setDestination);

        return itineraryRepository.save(itinerary);
    }

    public void deleteById(Long id) {
        itineraryRepository.deleteById(id);
    }
}
