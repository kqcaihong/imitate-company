package com.learn.more.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

  private static final String schema = "redis://";

  @Value("${redis.host}")
  private String host;

  @Value("${redis.port}")
  private int port;

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer().setAddress(schema + host + ":" + port)
        .setConnectTimeout(10000);
    return Redisson.create(config);
  }
}
