package com.up42.backendChallenge.features;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeaturesControllerTest {

    @Mock
    FeaturesRepository repository;

    @InjectMocks
    FeaturesController controller;

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void listFeatures() {
        // given
        Feature randomFeature = randomFeature();
        Mockito.when(repository.listFeatures()).thenReturn(Collections.singletonList(randomFeature));

        // when
        Collection<Feature> listFeatures = controller.listFeatures();

        // then
        assertThat(listFeatures)
                .hasSize(1)
                .contains(randomFeature);

        Mockito.verify(repository, Mockito.times(1)).listFeatures();
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getFeature() {
        // given
        Feature randomFeature = randomFeature();
        String randomFeatureId = randomFeature.getId();
        Mockito.when(repository.getFeature(randomFeatureId)).thenReturn(Optional.of(randomFeature));

        // when
        Feature feature = controller.getFeature(randomFeatureId);

        // then
        assertThat(feature).isEqualTo(randomFeature);

        Mockito.verify(repository, Mockito.times(1)).getFeature(randomFeatureId);
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void getQuickLook() {
        // given
        Feature randomFeature = randomFeature();
        String randomFeatureId = randomFeature.getId();
        Mockito.when(repository.getQuicklook(randomFeatureId)).thenReturn(Optional.of(randomFeature.getQuicklook()));

        // when
        byte[] feature = controller.getQuickLook(randomFeatureId);

        // then
        assertThat(feature).isEqualTo(randomFeature.getQuicklook());

        Mockito.verify(repository, Mockito.times(1)).getQuicklook(randomFeatureId);
        Mockito.verifyNoMoreInteractions(repository);
    }

    private Feature randomFeature() {
        return easyRandom.nextObject(Feature.class);
    }
}