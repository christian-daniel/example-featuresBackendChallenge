package com.up42.backendChallenge.features;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = FeaturesRepositoryImporter.class)
class FeaturesRepositoryImporterTest {

    @Autowired
    FeaturesRepositoryImporter importer;

    @Test
    void readDataFile() throws IOException {
        Map<String, Feature> data = importer.readDataFile();

        assertThat(data)
                .hasSize(14)
                .containsKeys("12d0b505-2c70-4420-855c-936d05c55669", "b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5");

        assertThat(data.get("12d0b505-2c70-4420-855c-936d05c55669").getQuicklook()).isNotNull();
        assertThat(data.get("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5").getQuicklook()).isNull();
    }
}