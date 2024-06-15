package com.learn.more.entiry.workflow;

public interface Workflow {

  String identifier();

  Object shopping(Object param);

  Object pricing(Object param);

  default Object booking(Object param) {
    throw new UnsupportedOperationException();
  }
}
