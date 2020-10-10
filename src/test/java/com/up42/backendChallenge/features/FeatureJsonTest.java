package com.up42.backendChallenge.features;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class FeatureJsonTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JacksonTester<Feature> json;

    @Test
    void testSerializedAttributes() throws IOException {
        // given
        Feature feature = new EasyRandom().nextObject(Feature.class);

        // when
        JsonContent<Feature> jsonContent = this.json.write(feature);

        // then
        assertThat(jsonContent).extractingJsonPathStringValue("$.id").isEqualTo(feature.getId());
        assertThat(jsonContent).extractingJsonPathNumberValue("$.timestamp").isEqualTo(feature.getTimestamp());
        assertThat(jsonContent).extractingJsonPathNumberValue("$.beginViewingDate").isEqualTo(feature.getBeginViewingDate());
        assertThat(jsonContent).extractingJsonPathNumberValue("$.endViewingDate").isEqualTo(feature.getEndViewingDate());
        assertThat(jsonContent).extractingJsonPathStringValue("$.missionName").isEqualTo(feature.getMissionName());
    }

    @Test
    void testNotSerializeQuicklook() throws IOException {
        // given
        Feature feature = new EasyRandom().nextObject(Feature.class);

        // when
        JsonContent<Feature> jsonContent = this.json.write(feature);

        // then
        assertThat(jsonContent).doesNotHaveJsonPath("$.quicklook");
    }

}