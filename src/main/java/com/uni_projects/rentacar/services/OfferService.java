package com.uni_projects.rentacar.services;

import com.uni_projects.rentacar.entities.Offer;
import com.uni_projects.rentacar.repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer fetchOfferById(int id) {
        return this.offerRepository.fetchSingle(id);
    }

    public Offer fetchOfferByUserId(int userId) {
        return this.offerRepository.fetchByUserId(userId);
    }

    public boolean createOffer(Offer offer) {
        return this.offerRepository.create(offer);
    }

    public boolean removeOffer(int id) {
        return this.offerRepository.remove(id);
    }
}
