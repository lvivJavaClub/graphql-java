package com.lohika.jclub.graphqljava.graphql;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class GraphQLController {

  private final GraphQL graphQL;

  @Autowired
  public GraphQLController(UserGraph userGraph) {
    GraphQLSchema schema = new GraphQLSchemaGenerator()
        .withResolverBuilders(new AnnotatedResolverBuilder())
        .withOperationsFromSingleton(userGraph)
        .withValueMapperFactory(new JacksonValueMapperFactory())
        .generate();

    graphQL = GraphQL.newGraphQL(schema)
        .queryExecutionStrategy(new BatchedExecutionStrategy())
        .instrumentation(new ChainedInstrumentation(Arrays.asList(
            new MaxQueryComplexityInstrumentation(200),
            new MaxQueryDepthInstrumentation(20)
        )))
        .build();
  }

  @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ExecutionResult execute(@RequestBody Map<String, Object> request) {
    return graphQL.execute(ExecutionInput.newExecutionInput()
        .query((String) request.get("query"))
        .operationName((String) request.get("operationName"))
        .variables((Map<String, Object>) request.get("variables"))
        .build());
  }

}
