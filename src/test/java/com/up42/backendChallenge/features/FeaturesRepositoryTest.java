package com.up42.backendChallenge.features;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeaturesRepositoryTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @Mock
    FeaturesRepositoryImporter importer;

    @Test
    void listFeatures() throws IOException {
        // given
        Feature feature1 = randomFeature();
        Feature feature2 = randomFeature();
        HashMap<String, Feature> featureMap = new HashMap<>();
        featureMap.put(feature1.getId(), feature1);
        featureMap.put(feature2.getId(), feature2);
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Collection<Feature> returnFeatureList = repository.listFeatures();

        // then
        assertThat(returnFeatureList)
                .hasSize(2)
                .contains(feature1, feature2);
    }

    @Test
    void getFeature() throws IOException {
        // given
        Feature feature1 = randomFeature();
        HashMap<String, Feature> featureMap = new HashMap<>();
        featureMap.put(feature1.getId(), feature1);
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Optional<Feature> returnFeature = repository.getFeature(feature1.getId());

        // then
        assertThat(returnFeature)
                .isNotEmpty()
                .hasValue(feature1);
    }

    @Test
    void getNonExistentFeature() throws IOException {
        // given
        HashMap<String, Feature> featureMap = new HashMap<>();
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Optional<Feature> returnFeature = repository.getFeature("anyRandomId");

        // then
        assertThat(returnFeature)
                .isEmpty();
    }

    @Test
    void getQuicklook() throws IOException {
        // given
        Feature feature1 = randomFeature();
        HashMap<String, Feature> featureMap = new HashMap<>();
        featureMap.put(feature1.getId(), feature1);
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Optional<byte[]> returnQuicklook = repository.getQuicklook(feature1.getId());

        // then
        assertThat(returnQuicklook)
                .isNotEmpty()
                .hasValue(feature1.getQuicklook());
    }

    @Test
    void getNonExistentQuicklook() throws IOException {
        // given
        Feature featureWithoutQuicklook = randomNullQuicklookFeature();
        HashMap<String, Feature> featureMap = new HashMap<>();
        featureMap.put(featureWithoutQuicklook.getId(), featureWithoutQuicklook);
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Optional<byte[]> returnQuicklook = repository.getQuicklook(featureWithoutQuicklook.getId());

        // then
        assertThat(returnQuicklook)
                .isEmpty();
    }

    @Test
    void getQuicklookFromNonExistentFeature() throws IOException {
        // given
        HashMap<String, Feature> featureMap = new HashMap<>();
        Mockito.when(importer.readDataFile()).thenReturn(featureMap);

        FeaturesRepository repository = new FeaturesRepository(importer);

        // when
        Optional<byte[]> returnQuicklook = repository.getQuicklook("anyRandomId");

        // then
        assertThat(returnQuicklook)
                .isEmpty();
    }

    private Feature randomFeature() {
        return easyRandom.nextObject(Feature.class);
    }

    private Feature randomNullQuicklookFeature() {
        return new Feature(easyRandom.nextObject(String.class), easyRandom.nextLong(), easyRandom.nextLong(), easyRandom.nextLong(),
                easyRandom.nextObject(String.class), null);
    }
}