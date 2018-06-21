package com.lohika.jclub.graphqljava;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lohika.jclub.graphqljava.domain.User;
import com.lohika.jclub.graphqljava.domain.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLJavaConfig {
  @Autowired
  private UserRepo userRepo;

  @Bean
  public GraphQLQueryResolver query() {
    return new GraphQLQueryResolver() {
      public String hi() {
        return "Hello,!";
      }

      public User user(Long id) {
        return userRepo.findOne(id);
      }
    };
  }

  @Bean
  public GraphQLMutationResolver mutation() {
    return new GraphQLMutationResolver() {
      public User createUser(Long id, String name) {
        return userRepo.save(User.builder().id(id)
            .name(name).build());
      }
    };
  }
}
