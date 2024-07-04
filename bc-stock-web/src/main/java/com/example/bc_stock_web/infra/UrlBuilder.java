package com.example.bc_stock_web.infra;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlBuilder {
  // public static String get(Scheme scheme, String domain, String endpoint) {
  //   return UriComponentsBuilder.newInstance() //
  //       .host(scheme.lowerCase()) //
  //       .toUriString();
  // }

  public static String get(Scheme scheme, String domain, String endpoint,
      String... pathSegments) {
    return UriComponentsBuilder.newInstance() //
        .host(scheme.lowerCase()) //
        .pathSegment(pathSegments) //
        .toUriString();
  }

  // public static String get(Scheme scheme, String domain, String endpoint,
  //     String queryParm, String queryValue, String... pathSegments) {
  //   return UriComponentsBuilder.newInstance() //
  //       .host(scheme.lowerCase()) //
  //       .pathSegment(pathSegments) //
  //       .queryParam(queryParm, queryValue) //
  //       .toUriString();
  // }

  // public static String get(Scheme scheme, String domain, String endpoint,
  //     MultiValueMap<String, String> params, String... pathSegments) {
  //   return UriComponentsBuilder.newInstance() //
  //       .host(scheme.lowerCase()) //
  //       .pathSegment(pathSegments) //
  //       .queryParams(params) //
  //       .toUriString();
  // }
}
