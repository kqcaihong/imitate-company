package com.learn.more.entiry.workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowRequest {

  private Object param;
  private String identifier;
}
