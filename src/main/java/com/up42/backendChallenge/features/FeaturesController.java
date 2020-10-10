package com.up42.backendChallenge.features;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class FeaturesController {

    public Collection<Feature> listFeatures() {
        return null;
    }

    public Optional<Feature> getFeature(String id) {
        return null;
    }

    public Optional<byte[]> getQuickLook (String id) {
        return null;
    }
}
