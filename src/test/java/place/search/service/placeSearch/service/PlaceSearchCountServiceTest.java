package place.search.service.placeSearch.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import place.search.service.placeSearch.entity.PlaceSearchCount;
import place.search.service.placeSearch.repository.PlaceSearchCountRepository;
import place.search.service.placeSearch.service.impl.PlaceSearchCountServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class PlaceSearchCountServiceTest {

    @InjectMocks
    private PlaceSearchCountServiceImpl placeSearchCountService;

    @Mock
    private PlaceSearchCountRepository placeSearchCountRepository;

    @Test
    void test_top_count_check() {
        PlaceSearchCount placeSearchCount1 = new PlaceSearchCount();
        placeSearchCount1.setQuery("치킨");
        placeSearchCount1.setCount(100L);

        PlaceSearchCount placeSearchCount2 = new PlaceSearchCount();
        placeSearchCount2.setQuery("삼겹살");
        placeSearchCount2.setCount(20L);

        PlaceSearchCount placeSearchCount3 = new PlaceSearchCount();
        placeSearchCount3.setQuery("곱창");
        placeSearchCount3.setCount(10L);

        List<PlaceSearchCount> placeSearchCountList = new ArrayList<>();
        placeSearchCountList.add(placeSearchCount1);
        placeSearchCountList.add(placeSearchCount2);
        placeSearchCountList.add(placeSearchCount3);

        Page<PlaceSearchCount> placeSearchCountPage = new PageImpl<>(placeSearchCountList);
        given(placeSearchCountRepository.findAll(any(PageRequest.class))).willReturn(placeSearchCountPage);

        List<PlaceSearchCount> topSearchedCounts = placeSearchCountService.getTopSearchedCounts();

        assertEquals(topSearchedCounts.get(0).getQuery(), "치킨");
        assertEquals(topSearchedCounts.get(1).getQuery(), "삼겹살");
        assertEquals(topSearchedCounts.get(2).getQuery(), "곱창");
    }
}
