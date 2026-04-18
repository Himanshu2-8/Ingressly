package com.himanshu.proxyServer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProxyResponse {
  private String message;
  private String proxyUrl;
  private String apiKey;
}
