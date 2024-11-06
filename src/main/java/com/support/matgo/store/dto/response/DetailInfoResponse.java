package com.support.matgo.store.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DetailInfoResponse {
  private SimpleInfoResponse simpleData;
  List<String> imgList;
}
