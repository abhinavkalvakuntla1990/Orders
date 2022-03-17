package org.egen.ecommerce.logging;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TraceIdGenerator {
  private static final String SERVICE_TRACE_ID_NAME = "order";

  /**
   * Generate a unique traceID with the service name
   * @return traceId
   */
  public String getTraceIdIdGenerator() {
    return String.join("-", SERVICE_TRACE_ID_NAME, UUID.randomUUID().toString());
  }
}


