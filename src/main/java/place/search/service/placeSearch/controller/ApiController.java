package place.search.service.placeSearch.controller;

import org.springframework.web.bind.annotation.RequestParam;
import place.search.service.placeSearch.dto.api.PlaceDto;
import place.search.service.placeSearch.dto.common.Response;
import place.search.service.placeSearch.service.impl.PlaceSearchCountServiceImpl;
import place.search.service.placeSearch.service.impl.PlaceSearchServiceImpl;
import place.search.service.placeSearch.entity.PlaceSearchCount;

import java.util.List;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1.0")
public class ApiController {

  private final PlaceSearchServiceImpl placeSearchServiceImpl;
  private final PlaceSearchCountServiceImpl placeSearchCountServiceImpl;

  @GetMapping("/places")
  public Response<List<PlaceDto>> searchPlaces(
          @RequestParam String query) {

    List<PlaceDto> placeData = placeSearchServiceImpl.getPlaces(query);
    placeSearchCountServiceImpl.addCount(query);

    return Response.success(placeData);
  }

  @GetMapping("/places/search/rank/lists")
  public Response<List<PlaceSearchCount>> searchTopPlacesCounts() {
    return Response.success(placeSearchCountServiceImpl.getTopSearchedCounts());
  }
}
