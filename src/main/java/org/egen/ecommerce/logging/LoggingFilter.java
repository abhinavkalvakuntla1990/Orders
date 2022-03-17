package org.egen.ecommerce.logging;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Order
@Component
public class LoggingFilter implements Filter {
  private static final String X_TRACE_ID = "X-Trace-ID";
  private static final String TRACE_ID = "TraceId";

  private TraceIdGenerator traceIdGenerator;

  @Autowired
  public LoggingFilter(TraceIdGenerator traceIdGenerator) {
    this.traceIdGenerator = traceIdGenerator;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
    if (req instanceof HttpServletRequest) {
      String traceId = ((HttpServletRequest) req).getHeader(X_TRACE_ID);

      if (Objects.isNull(traceId)) {
        traceId = traceIdGenerator.getTraceIdIdGenerator();
      }

      MDC.put(TRACE_ID, traceId);
    }

    try {
      chain.doFilter(req, res);
    } finally {
      MDC.remove(TRACE_ID);
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // do nothing
  }

  @Override
  public void destroy() {
    // do nothing
  }
}
