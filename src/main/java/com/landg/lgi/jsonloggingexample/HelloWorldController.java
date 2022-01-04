package com.landg.lgi.jsonloggingexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class HelloWorldController {
  private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

  @RequestMapping("/hello")
  @ResponseBody
  public String sayHello(@RequestParam Optional<String> name) {
    try {
      MDC.put("quote.id", "X12345");
      MDC.put("quote.agent", "Y98765");

      logger.debug("Hello from Logback {}", Arrays.asList(1, 2, 3, 4, 5));
      logger.error("Hello from Logback {}", Arrays.asList(1, 2, 3, 4, 5));

      return "Hello, " + name.orElse("Mysterious Person") + "!";

    } finally {
      MDC.clear();
    }
  }
}
