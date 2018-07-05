package com.lohika.jclub.graphqljava.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lohika.jclub.graphqljava.domain.User;
import com.lohika.jclub.graphqljava.domain.UserRepo;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
  public UserDto user(@GraphQLArgument(name = "id") Long id) {
    return new UserDto(userRepo.findOne(id));
  }


  @GraphQLQuery(name = "friends")
  public Set<UserDto> friends(@GraphQLContext UserDto userDto,
                              @GraphQLEnvironment Set<String> subFields,
                              @GraphQLEnvironment graphql.language.Field field
  ) {
    log.info("user dto {}", userDto);
    log.info("subFields {}", subFields);
    log.info("field {}", field);

    User user = userRepo.findOne(userDto.getId());

    Iterable<User> userIterable = userRepo.findAll(user.getFriends());

    return StreamSupport.stream(userIterable.spliterator(), false)
        .map(UserDto::new)
        .collect(Collectors.toSet());
  }


  @GraphQLMutation(name = "createUser")
  public User createUser(@GraphQLArgument(name = "id") Long id,
                         @GraphQLArgument(name = "name") String name) {
    User user = User.builder()
        .id(id)
        .name(name)
        .build();
    return userRepo.save(user);
  }

  @GraphQLMutation(name = "createFriends")
  public void createFriends(@GraphQLArgument(name = "id1") Long id1,
                            @GraphQLArgument(name = "id2") Long id2) {

    User user1 = userRepo.findOne(id1);
    User user2 = userRepo.findOne(id2);

    user1.getFriends().add(id2);
    user2.getFriends().add(id1);

    userRepo.save(user1);
    userRepo.save(user2);
  }
}
