package org.egen.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Orders API",  version = "1.0", description = "Orders information"))
@EnableJpaAuditing
public class OrderApplication {
  public static void main(String[] args) { SpringApplication.run(OrderApplication.class, args); }
}
