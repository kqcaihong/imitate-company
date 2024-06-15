package com.learn.more.entiry.workflow;

import org.springframework.stereotype.Service;

@Service
public class AWorkflow implements Workflow {

  @Override
  public String identifier() {
    return "A";
  }

  @Override
  public Object shopping(Object param) {
    return "shopping A";
  }

  @Override
  public Object pricing(Object param) {
    return "pricing A";
  }
}
