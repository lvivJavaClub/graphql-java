package com.lohika.jclub.graphqljava.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@Document
public class User {
  private Long id;

  private String name;

  @Builder.Default
  private Set<Long> friends = new HashSet<>();
}
