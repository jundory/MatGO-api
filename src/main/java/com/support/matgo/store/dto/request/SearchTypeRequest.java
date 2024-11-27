package com.support.matgo.store.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class SearchTypeRequest extends CoordinatesRequest {
  private List<String> member;
  private List<String> food;
}
