package com.learn.more.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Map<String, Object> error(Exception e) {
    Map<String, Object> map = new HashMap<>();
    map.put("status", false);
    map.put("msg", e.getMessage());
    return map;
  }
}
