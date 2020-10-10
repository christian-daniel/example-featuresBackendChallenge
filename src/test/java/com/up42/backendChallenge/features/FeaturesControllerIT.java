package com.up42.backendChallenge.features;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeaturesControllerIT {

    @Autowired
    MockMvc mvc;

    @Test
    void listFeatures() throws Exception {
        mvc.perform(get("/features"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(14)))
                .andExpect(jsonPath("$[0].id", is(not(emptyOrNullString()))))
                .andExpect(jsonPath("$[0].timestamp", is(instanceOf(Long.class))))
                .andExpect(jsonPath("$[0].beginViewingDate", is(instanceOf(Long.class))))
                .andExpect(jsonPath("$[0].endViewingDate", is(instanceOf(Long.class))))
                .andExpect(jsonPath("$[0].missionName", is(not(emptyOrNullString()))))
                .andExpect(jsonPath("$[0].quicklook").doesNotExist());
    }

    @Test
    void getFeature() throws Exception {
        mvc.perform(get("/features/12d0b505-2c70-4420-855c-936d05c55669"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("12d0b505-2c70-4420-855c-936d05c55669")))
                .andExpect(jsonPath("$.timestamp", is(1555477219508L)))
                .andExpect(jsonPath("$.beginViewingDate", is(1555477219508L)))
                .andExpect(jsonPath("$.endViewingDate", is(1555477244506L)))
                .andExpect(jsonPath("$.missionName", is("Sentinel-1A")))
                .andExpect(jsonPath("$.quicklook").doesNotExist());
    }

    @Test
    void getNonExistentFeature() throws Exception {
        mvc.perform(get("/features/anyRandomId"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("Feature Not Found")))
                .andExpect(status().reason(containsString("anyRandomId")));
    }

    @Test
    void getQuickLook() throws Exception {
        mvc.perform(get("/features/12d0b505-2c70-4420-855c-936d05c55669/quicklook"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().string(containsString("PNG")));
    }

    @Test
    void getNonExistentQuickLook() throws Exception {
        mvc.perform(get("/features/b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5/quicklook"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("Quicklook Not Found")))
                .andExpect(status().reason(containsString("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5")));
    }

    @Test
    void getQuickLookNonExistentFeature() throws Exception {
        mvc.perform(get("/features/anyRandomId/quicklook"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("Quicklook Not Found")))
                .andExpect(status().reason(containsString("anyRandomId")));
    }
}