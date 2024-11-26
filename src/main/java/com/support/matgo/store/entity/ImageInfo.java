package com.support.matgo.store.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "image_meta")
@Getter
//@NoArgsConstructor  // NoSQL에서 entity 객체 인스턴스화할 때 기본 생성자 필요하기 때문
public class ImageInfo {
  @Field("img_url")
  private String imgUrl;

  @Field("store_id")
  private String storeId;

  @Field("tags")
  private List<String> tags;
}
