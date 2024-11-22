package com.uni_projects.rentacar.controllers;

import com.uni_projects.rentacar.entities.Offer;
import com.uni_projects.rentacar.http.AppResponse;
import com.uni_projects.rentacar.services.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<?> fetchSingleOffer(@PathVariable int id) {
        Offer offerResponse = this.offerService.fetchOfferById(id);

        if (offerResponse != null) {
            return AppResponse.success()
                    .withDataAsArray(offerResponse)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Could not find offer")
                .build();
    }

    @GetMapping("/offers/calculate/{id}")
    public ResponseEntity<?> calculateSingleOffer(@PathVariable int id) {
        Offer offerResponse = this.offerService.fetchOfferById(id);
        Offer calculatedOffer = this.offerService.calculatePrice(offerResponse);

        if (calculatedOffer != null) {
            return AppResponse.success()
                    .withDataAsArray(calculatedOffer)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Could not find offer")
                .build();
    }

    @GetMapping("/offers/user/{userId}")
    public ResponseEntity<?> fetchOfferByUserId(@PathVariable int userId) {
        Offer userOfferResponse = this.offerService.fetchOfferByUserId(userId);
        if (userOfferResponse != null) {
            return AppResponse.success()
                    .withDataAsArray(userOfferResponse)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Could not find offer for this user")
                .build();
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        boolean isOfferCreated = this.offerService.createOffer(offer);

        if(isOfferCreated) {
            return AppResponse.success()
                    .withDataAsArray(offer)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Offer could not be created")
                .build();
    }

    @PutMapping("/offers")
    public ResponseEntity<?> acceptOffer(@RequestBody Offer offer) {
        boolean isUpdateSuccess = this.offerService.activateOffer(offer);

        if(!isUpdateSuccess) {
            return AppResponse.success()
                    .withMessage("Offer accepted")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Offer could not be accepted")
                .build();
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable int id) {
        boolean isUpdateSuccessfully = this.offerService.removeOffer(id);

        if(isUpdateSuccessfully) {
            return AppResponse.success()
                    .withMessage("Offer has been deleted successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Offer can not be deleted")
                .build();
    }
}
