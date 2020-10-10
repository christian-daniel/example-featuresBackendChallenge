package com.up42.backendChallenge.features;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class Feature {
    String id;
    Long timestamp;
    Long beginViewingDate;
    Long endViewingDate;
    String missionName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    byte[] quicklook;
}
