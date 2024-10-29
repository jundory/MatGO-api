package com.support.matgo.restaurantInfo.controller;

import com.support.matgo.restaurantInfo.dto.searchTypeDTO;
import com.support.matgo.restaurantInfo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StoreController {
  private final StoreService storeService;

//  @PostMapping("/getMainList")
//  public ResponseEntity<?> mainFeedList(){}

  @PostMapping("/getSearchList")
  public ResponseEntity<?> searchStoreList(@RequestBody searchTypeDTO param) {
    return storeService.findRestaurantList(param);
  }
}
