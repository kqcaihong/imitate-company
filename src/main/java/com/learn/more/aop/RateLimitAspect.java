package com.learn.more.aop;

import com.learn.more.annotation.RateLimit;
import com.learn.more.config.RateLimiter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class RateLimitAspect {

  private static final DefaultParameterNameDiscoverer DISCOVERER = new DefaultParameterNameDiscoverer();
  private static final String TIP = "hit to %s limit, qps is %s for per second";

  @Autowired
  private RateLimiter rateLimiter;

  // 这样写也可以
/*    @Before("@annotation(rateLimit)")
  public void before(JoinPoint joinPoint, RateLimit rateLimit){

    }*/

  @Pointcut("@annotation(com.learn.more.annotation.RateLimit)")
  public void rateLimit() {
  }

  @Before("rateLimit()")
  public void before(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String[] parameterNames = DISCOVERER.getParameterNames(signature.getMethod());
    RateLimit rateLimit = signature.getMethod().getAnnotation(RateLimit.class);
    List<String> keys = new ArrayList<>();
    String[] params = rateLimit.keys();
    if (params.length > 0 && parameterNames.length > 0) {
      Object[] args = joinPoint.getArgs();
      Set<String> paramsSet = new HashSet<>(Arrays.asList(params));
      for (int i = 0; i < parameterNames.length; i++) {
        if (paramsSet.contains(parameterNames[i])) {
          keys.add(args[i].toString());
        }
      }
    }

    String key = String.join("-", keys);
    if (!rateLimiter.pass(key)) {
      throw new RuntimeException(String.format(TIP, rateLimit.name(), rateLimiter.qps(key)));
    }
  }
}
