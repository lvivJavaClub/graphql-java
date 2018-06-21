package com.lohika.jclub.graphqljava.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@Document
public class User {
  private Long id;

  private String name;
}
