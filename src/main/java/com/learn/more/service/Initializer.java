package com.learn.more.service;

import com.learn.more.entiry.workflow.Workflow;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class Initializer implements ApplicationRunner {

  private final Dispatcher dispatcher;
  private final List<Workflow> workflows;

  public Initializer(Dispatcher dispatcher, List<Workflow> workflows) {
    this.dispatcher = dispatcher;
    this.workflows = workflows;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    for (Workflow workflow : workflows) {
      dispatcher.register(new Tenant(workflow.identifier(), workflow));
    }
  }
}
