package com.learn.more.controller;

import com.learn.more.entiry.workflow.WorkflowRequest;
import com.learn.more.service.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/workflow")
@RestController
public class WorkflowController {

  @Autowired
  private Dispatcher dispatcher;

  @PostMapping("/shopping")
  public Object shopping(@RequestBody WorkflowRequest request) {
    return dispatcher.dispatch(request.getIdentifier()).getWorkflow().shopping(request.getParam());
  }

  @PostMapping("/pricing")
  public Object pricing(@RequestBody WorkflowRequest request) {
    return dispatcher.dispatch(request.getIdentifier()).getWorkflow().pricing(request.getParam());
  }
}
