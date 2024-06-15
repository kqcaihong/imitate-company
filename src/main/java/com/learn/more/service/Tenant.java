package com.learn.more.service;

import com.learn.more.entiry.workflow.Workflow;
import java.util.Set;
import lombok.Getter;

// 对workflow的包装
@Getter
public class Tenant {

  // 唯一标识
  private String identifier;
  // 适用集
  private Set<Long> targetIds;
  // 动作集，非必须
  private Workflow workflow;

  public Tenant(String identifier, Workflow workflow) {
    this.identifier = identifier;
    this.workflow = workflow;
  }
}
