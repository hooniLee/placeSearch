package place.search.service.placeSearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import place.search.service.placeSearch.service.PlaceSearchCountService;
import place.search.service.placeSearch.entity.PlaceSearchCount;
import place.search.service.placeSearch.repository.PlaceSearchCountRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceSearchCountServiceImpl implements PlaceSearchCountService {

    private static final Long FIRST_INPUT_COUNT = 1L;

    private static final int FIRST_PAGE_NUM = 0;
    private static final int MAX_TOP_SEARCH_COUNT = 10;
    private static final String ORDER_COLUMN = "count";
    private final PlaceSearchCountRepository placeSearchCountRepository;

    @Override
    public void addCount(String query) {
        String trimmedQuery = query.trim();
        if (!placeSearchCountRepository.findById(trimmedQuery).isEmpty()) {
            placeSearchCountRepository.save(new PlaceSearchCount(trimmedQuery,placeSearchCountRepository.findById(trimmedQuery).get().getCount() + 1));
        } else {
            placeSearchCountRepository.save(new PlaceSearchCount(trimmedQuery, FIRST_INPUT_COUNT));
        }
    }

    @Override
    public List<PlaceSearchCount> getTopSearchedCounts() {
        List<PlaceSearchCount> placeSearchCounts =
                placeSearchCountRepository
                        .findAll(
                                PageRequest.of(
                                        FIRST_PAGE_NUM, MAX_TOP_SEARCH_COUNT, Sort.by(Sort.Order.desc(ORDER_COLUMN))))
                        .getContent();

        return placeSearchCounts;
    }
}
