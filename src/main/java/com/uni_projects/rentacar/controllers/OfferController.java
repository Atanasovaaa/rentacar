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

        if(this.offerService.createOffer(offer)) {
            return AppResponse.success()
                    .withMessage("Offer successfully created")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Offer could not be created")
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
