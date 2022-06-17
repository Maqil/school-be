package com.school.graphql.controller;

import com.school.graphql.service.Mutation;
import com.school.graphql.service.Query;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import com.school.graphql.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GraphQLController {
    private final GraphQL graphQL;

    public GraphQLController(UserService userService, Query query, Mutation mutation) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(userService, UserService.class)
                .withOperationsFromSingleton(query, Query.class)
                .withOperationsFromSingleton(mutation, Mutation.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest raw) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
//                .query(request.get("query"))
//                .operationName(request.get("operationName"))
                .query((String) request.get("query"))
                .operationName((String) request.get("operationName"))
//                .variables((Map<String, Object>) request.get("variables"))
                .context(raw)
                .build());
        return executionResult.toSpecification();
    }
}
