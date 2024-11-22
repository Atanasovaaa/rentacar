package com.uni_projects.rentacar.services;

import com.uni_projects.rentacar.entities.Offer;
import com.uni_projects.rentacar.repositories.CarRepository;
import com.uni_projects.rentacar.repositories.OfferRepository;
import com.uni_projects.rentacar.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, CarRepository carRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
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

    public Offer calculatePrice(Offer offer) {
        Number carPricePerDay = this.carRepository.fetchSingle(offer.getCarId()).getPricePerDay();

        int rentDays = offer.getRentDays();
        int userCrashes = this.userRepository.fetchSingle(offer.getUserId()).getHasCrashes();

        BigDecimal bigNumber = BigDecimal.valueOf(carPricePerDay.doubleValue());

        BigDecimal result = bigNumber.multiply(BigDecimal.valueOf(rentDays));

        if (userCrashes == 1) {
            result = result.add(BigDecimal.valueOf(200));
        }

        if(!this.offerRepository.updateTotalPrice(result, offer.getId())) {
            return null;
        }

        return offer;
    }

    public boolean activateOffer(Offer offer) {
        return this.offerRepository.activate(offer);
    }

    public boolean removeOffer(int id) {
        return this.offerRepository.remove(id);
    }
}
