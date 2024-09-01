package com.portfolio.mutex.srms.config;

import com.portfolio.mutex.srms.dto.ExceptionDto;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("SRMS APIs").version("1.0")
            .description("Rest APIs for Student Record Management System"))
        .servers(Collections.singletonList(new Server().url("/")))
        .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))
        .components(new Components().addSecuritySchemes("jwtAuth",
            new SecurityScheme().type(Type.APIKEY).name(HttpHeaders.AUTHORIZATION).in(In.HEADER)
                .description("Prefix the value with `Bearer` e.g., Bearer <JWT>")));
  }

  @Bean
  public OpenApiCustomizer openApiCustomizer() {
    return openApi -> {
      openApi.getComponents().getSchemas()
          .putAll(ModelConverters.getInstance().read(ExceptionDto.class));
      openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
          .forEach(operation -> operation.getResponses()
              .addApiResponse("401", new ApiResponse().description("Authentication failed.")
                  .content(new Content().addMediaType("application/json",
                      new MediaType().schema(new Schema<ExceptionDto>().name("ExceptionDto")
                              .$ref("#/components/schemas/ExceptionDto"))
                          .example("""
                              {
                                "message": "Failed to authenticate user."
                              }
                              """))))
              .addApiResponse("500", new ApiResponse().description("Internal Server Error")
                  .content(new Content().addMediaType("application/json",
                      new MediaType().schema(new Schema<ExceptionDto>().name("ExceptionDto")
                              .$ref("#/components/schemas/ExceptionDto"))
                          .example("""
                              {
                                "message": "Server has encountered an error it doesn't know how to handle."
                              }
                              """))))));
    };
  }
}
