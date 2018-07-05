package com.lohika.jclub.graphqljava.graphql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import com.lohika.jclub.graphqljava.domain.User;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
public class UserDto {
  private Long id;

  private String name;

  @Builder.Default
  private Set<UserDto> friends = new HashSet<>();

  public UserDto(User user) {
    name = user.getName();
    id = user.getId();
  }
}