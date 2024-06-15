package com.learn.more.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiter {

  public static final String KEY_PREFIX = "global:rate:limit:";

  private final static Map<String, Integer> QPS_MAP = new HashMap<>();

  @Autowired
  private RedissonClient redissonClient;

  @Value("${qps.config}")
  public void qpsConfig(String qpsConfig) {
    for (String limit : qpsConfig.split(";")) {
      String[] split = limit.split(":");
      RRateLimiter limiter = redissonClient.getRateLimiter(KEY_PREFIX + split[0]);
      int qps = Integer.parseInt(split[1]);
      while (!limiter.trySetRate(RateType.OVERALL, qps, 1, RateIntervalUnit.MINUTES)) {
        limiter.delete();
        limiter = redissonClient.getRateLimiter(KEY_PREFIX + split[0]);
      }
      QPS_MAP.put(split[0], qps);
    }
  }

  public Integer qps(String key) {
    return QPS_MAP.get(key);
  }

  public boolean pass(String key) {
    if (QPS_MAP.containsKey(key)) {
      RRateLimiter limiter = redissonClient.getRateLimiter(KEY_PREFIX + key);
      return limiter.tryAcquire(1, TimeUnit.SECONDS);
    }
    return true;
  }
}
