package place.search.service.placeSearch.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import place.search.service.placeSearch.exception.ClientException;
import place.search.service.placeSearch.service.impl.PlaceSearchCountServiceImpl;
import place.search.service.placeSearch.service.impl.PlaceSearchServiceImpl;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class ApiControllerTest {

    @Autowired
    private ApiController apiController;
    private MockMvc mockMvc;

    @MockBean
    private PlaceSearchServiceImpl placeSearchServiceImpl;
    @MockBean
    private PlaceSearchCountServiceImpl placeSearchCountServiceImpl;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(apiController)
                .setControllerAdvice(new ExceptionHandlers())
                .build();

        given(placeSearchServiceImpl.getPlaces(any(String.class))).willReturn(new ArrayList<>());
        given(placeSearchCountServiceImpl.getTopSearchedCounts()).willReturn(new ArrayList<>());
    }

    @Test
    void test_places_success() throws Exception {

        mockMvc
                .perform(
                        get("/v1.0/places?query=치킨"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.resultMessage").value("OK"));

        verify(placeSearchServiceImpl).getPlaces(any(String.class));
        verify(placeSearchCountServiceImpl).addCount(any(String.class));
    }

    @Test
    void test_places_client_error() throws Exception {
        given(placeSearchServiceImpl.getPlaces(any(String.class))).willThrow(new ClientException(new Exception()));

        mockMvc
                .perform(get("/v1.0/places?query="))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.header.resultMessage")
                                .value("Internal Server Error"));
    }

    @Test
    void test_top_searched_counts_success() throws Exception {

        mockMvc
                .perform(
                        get("/v1.0/places/search/rank/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.resultMessage").value("OK"));

        verify(placeSearchCountServiceImpl).getTopSearchedCounts();
    }
}
