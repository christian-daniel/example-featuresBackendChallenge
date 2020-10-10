package com.up42.backendChallenge.features;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
class FeaturesRepository {

    Collection<Feature> listFeatures() {
        return null;
    }

    Optional<Feature> getFeature(String id) {
        return null;
    }

    Optional<byte[]> getQuicklook(String id) {
        return null;
    }
}
