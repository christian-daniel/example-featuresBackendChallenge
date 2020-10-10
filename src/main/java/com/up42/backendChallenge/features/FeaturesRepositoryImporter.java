package com.up42.backendChallenge.features;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
class FeaturesRepositoryImporter {

    @Value("classpath:${com.up42.backendchallenge.jsonDataFile}")
    private File jsonDataFile;

    Map<String, Feature> readDataFile() throws IOException {
        HashMap<String, Feature> ret = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode root = objectMapper.readTree(jsonDataFile);
        readJsonFile(ret, root);

        return Collections.unmodifiableMap(ret);
    }

    private void readJsonFile(final HashMap<String, Feature> ret, final JsonNode root) {
        Iterator<JsonNode> rootArray = root.elements();
        while (rootArray.hasNext()) {
            JsonNode level1Element = rootArray.next();
            Iterator<JsonNode> featureList = level1Element.get("features").elements();
            while (featureList.hasNext()) {
                JsonNode featureJson = featureList.next();
                Feature feature = readFeatureFromJson(featureJson);
                ret.put(feature.getId(), feature);
            }
        }
    }

    private Feature readFeatureFromJson(final JsonNode feature) {
        JsonNode featureProperties = feature.get("properties");

        String id = featureProperties.get("id").asText();
        Long timestamp = featureProperties.get("timestamp").asLong();
        Long beginViewingDate = featureProperties.get("acquisition").get("beginViewingDate").asLong();
        Long endViewingDate = featureProperties.get("acquisition").get("endViewingDate").asLong();
        String missionName = featureProperties.get("acquisition").get("missionName").asText();

        boolean hasQuicklook = featureProperties.has("quicklook");
        byte[] quicklook = null;
        if (hasQuicklook) {
            String quicklookString = featureProperties.get("quicklook").asText();
            quicklook = Base64.getDecoder().decode(quicklookString);
        }

        return new Feature(id, timestamp, beginViewingDate, endViewingDate, missionName, quicklook);
    }
}
