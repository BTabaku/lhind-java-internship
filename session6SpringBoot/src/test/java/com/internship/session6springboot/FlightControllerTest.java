// FlightControllerTest.java
package com.internship.session6springboot;

import com.internship.session6springboot.controller.FlightController;
import com.internship.session6springboot.dto.FlightDTO;
import com.internship.session6springboot.enums.BookingStatus;
import com.internship.session6springboot.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private final String sampleFlightJson = """
            {
                "origin": "JFK",
                "destination": "LAX",
                "status": "CONFIRMED"
            }
            """;

    @Test
    void createFlight_ShouldReturnCreated() throws Exception {
        FlightDTO responseDTO = new FlightDTO();
        responseDTO.setId(1L);
        responseDTO.setOrigin("JFK");
        responseDTO.setStatus(BookingStatus.CONFIRMED);

        when(flightService.createFlight(any(FlightDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/flights").contentType(MediaType.APPLICATION_JSON).content(sampleFlightJson)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.origin").value("JFK"));
    }

    @Test
    void getFlight_WhenExists_ShouldReturnFlight() throws Exception {
        FlightDTO responseDTO = new FlightDTO();
        responseDTO.setId(1L);
        responseDTO.setOrigin("LHR");

        when(flightService.getFlightById(anyLong())).thenReturn(Optional.of(responseDTO));

        mockMvc.perform(get("/api/flights/1")).andExpect(status().isOk()).andExpect(jsonPath("$.origin").value("LHR"));
    }

    @Test
    void getFlight_WhenNotExists_ShouldReturnNotFound() throws Exception {
        when(flightService.getFlightById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/flights/999")).andExpect(status().isNotFound());
    }
}