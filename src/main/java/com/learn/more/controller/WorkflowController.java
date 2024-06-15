package com.learn.more.controller;

import com.learn.more.annotation.RateLimit;
import com.learn.more.entiry.workflow.WorkflowRequest;
import com.learn.more.service.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/workflow")
@RestController
public class WorkflowController {

  @Autowired
  private Dispatcher dispatcher;

  @RateLimit(name = "shopping", keys = {"id"})
  @PostMapping("/shopping/{id}")
  public Object shopping(@RequestBody WorkflowRequest workflowRequest, @PathVariable String id) {
    return dispatcher.dispatch(workflowRequest.getIdentifier()).getWorkflow().shopping(workflowRequest.getParam());
  }

  @PostMapping("/pricing")
  public Object pricing(@RequestBody WorkflowRequest workflowRequest) {
    return dispatcher.dispatch(workflowRequest.getIdentifier()).getWorkflow().pricing(workflowRequest.getParam());
  }
}
