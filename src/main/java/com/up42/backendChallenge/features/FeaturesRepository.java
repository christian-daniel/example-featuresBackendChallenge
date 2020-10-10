package com.up42.backendChallenge.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
class FeaturesRepository {

    private final Map<String, Feature> repository;

    @Autowired
    public FeaturesRepository(FeaturesRepositoryImporter importer) throws IOException {
        this.repository = importer.readDataFile();
    }

    Collection<Feature> listFeatures() {
        return repository.values();
    }

    Optional<Feature> getFeature(String id) {
        return Optional.ofNullable(repository.get(id));
    }

    Optional<byte[]> getQuicklook(String id) {
        Optional<Feature> feature = this.getFeature(id);
        return feature.map(Feature::getQuicklook);
    }
}
