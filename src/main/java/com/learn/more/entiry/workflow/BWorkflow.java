package com.learn.more.entiry.workflow;

import org.springframework.stereotype.Service;

@Service
public class BWorkflow implements Workflow{
  @Override
  public String identifier() {
    return "B";
  }

  @Override
  public Object shopping(Object param) {
    return "shopping B";
  }

  @Override
  public Object pricing(Object param) {
    return "pricing B";
  }
}
