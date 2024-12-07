package com.jhw.rate.limit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        // define the limit 100 times per 1 minute
        Bandwidth limit = Bandwidth.simple(1, Duration.ofSeconds(1));
        // construct the bucket
        Bucket bucket = Bucket.builder().addLimit(limit).build();


        // do polling in infinite loop
        while (true) {
            // Consume a token from the token bucket.
            // If a token is not available this method will block until the refill adds one to the bucket.
            bucket.asBlocking().consume(1);
            System.out.println(LocalDateTime.now());
        }
    }
}
