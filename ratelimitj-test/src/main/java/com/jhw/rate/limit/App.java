package com.jhw.rate.limit;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 50 request per minute, per key
        Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(Duration.ofSeconds(1), 1));
        RequestRateLimiter requestRateLimiter = new InMemorySlidingWindowRequestRateLimiter(rules);

        // do polling in infinite loop
        while (true) {
            if (!requestRateLimiter.geLimitWhenIncremented("ip:127.0.0.2")) {
                System.out.println(LocalDateTime.now());
            }
        }

    }


}
