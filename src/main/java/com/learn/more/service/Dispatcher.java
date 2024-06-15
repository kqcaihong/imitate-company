package com.learn.more.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

// 注册、分发
@Service
public class Dispatcher {

  private final Map<String, Tenant> tenantMap = new HashMap<>();

  public void register(Tenant tenant) {
    tenantMap.put(tenant.getIdentifier(), tenant);
  }

  public Tenant dispatch(String identifier) {
    return tenantMap.get(identifier);
  }
}
