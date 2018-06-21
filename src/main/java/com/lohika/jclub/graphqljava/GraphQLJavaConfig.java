package com.lohika.jclub.graphqljava;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLJavaConfig {
  @Bean
  public GraphQLQueryResolver query() {
    return new GraphQLQueryResolver() {
      public String hi() { return "Hello,!"; }
    };
  }
}
