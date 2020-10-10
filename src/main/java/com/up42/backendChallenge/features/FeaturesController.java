package com.up42.backendChallenge.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/features", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeaturesController {

    private FeaturesRepository featuresRepository;

    @Autowired
    public FeaturesController(final FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    @GetMapping
    public Collection<Feature> listFeatures() {
        return featuresRepository.listFeatures();
    }

    @GetMapping("/{id}")
    public Feature getFeature(@PathVariable("id") String id) {
        return featuresRepository.getFeature(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feature Not Found - id: " + id));
    }

    @GetMapping(path = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQuickLook (@PathVariable("id") String id) {
        return featuresRepository.getQuicklook(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quicklook Not Found - id: " + id));
    }
}
