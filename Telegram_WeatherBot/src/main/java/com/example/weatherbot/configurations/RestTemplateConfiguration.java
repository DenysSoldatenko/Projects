package com.example.weatherbot.configurations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for providing a RestTemplate bean.
 */
@Configuration
public class RestTemplateConfiguration {

  /**
   * Creates and configures a {@link RestTemplate} bean with a custom JSON message converter.
   *
   * <p>This configuration ignores unknown properties in the response JSON.
   *
   * @return a configured {@link RestTemplate} instance.
   */
  @Bean
  public RestTemplate restTemplate() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);

    List<HttpMessageConverter<?>> converters = new ArrayList<>();
    converters.add(converter);

    return new RestTemplate(converters);
  }
}
