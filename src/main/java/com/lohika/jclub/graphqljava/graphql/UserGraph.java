package com.lohika.jclub.graphqljava.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lohika.jclub.graphqljava.domain.User;
import com.lohika.jclub.graphqljava.domain.UserRepo;

import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserGraph {

  private final UserRepo userRepo;

  @GraphQLQuery(name = "hi")
  public String hi() {
    return "my string";
  }

  @GraphQLQuery(name = "user")
  public User user(@GraphQLArgument(name = "id") Long id) {
    return userRepo.findOne(id);
  }

  @GraphQLMutation(name = "createUser")
  public User createUser(@GraphQLArgument(name = "id") Long id,
                         @GraphQLArgument(name = "name") String name) {
    return userRepo.save(User.builder().id(id)
        .name(name).build());
  }
}
