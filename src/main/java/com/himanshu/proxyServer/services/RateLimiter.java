package com.himanshu.proxyServer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RateLimiter {

    private final StringRedisTemplate redisTemplate;

    public boolean isAllowed(String domain) {

        String key = "rate_limit:" + domain;

        int maxLimit = 1000;

        Long currentCount = redisTemplate.opsForValue().increment(key, 1);

        if (currentCount == 1) {
            redisTemplate.expire(key, 1, TimeUnit.SECONDS);
        } else if (redisTemplate.getExpire(key, TimeUnit.SECONDS) == -1) {
            redisTemplate.expire(key, 1, TimeUnit.SECONDS);
        }

        return currentCount <= maxLimit;

    }

}
