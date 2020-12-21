/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.rate.GuavaRateLimiter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GuavaRateLimiter
 *
 * @Description GuavaRateLimiter {@link com.google.common.util.concurrent.RateLimiter}
 * @Author wangchonglin
 * @Date 2020-12-15 19:01:11
 * @Version 1.0.0
 */
@SuppressWarnings("UnstableApiUsage")
@Slf4j
public class GuavaRateLimiter implements IRateStrategy {

    /**
     * url method rateLimiter
     */
    private Map<String, Map<String, RateLimiter>> rateMap = new ConcurrentHashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean tryAcquire(String url, String method) {
        for (Map.Entry<String, Map<String, RateLimiter>> entry: rateMap.entrySet()) {
            if (antPathMatcher.match(entry.getKey(), url)) {
                RateLimiter rateLimiter = entry.getValue().get(method);
                if (!ObjectUtils.isEmpty(rateLimiter)) {
                    return rateLimiter.tryAcquire();
                }
            }
        }
        return true;
    }

    @Override
    public void addRateLimit(String url, String method, double permitsPerSecond) {
        Map<String, RateLimiter> map = rateMap.get(url);
        if (ObjectUtils.isEmpty(map)) {
            map = new ConcurrentHashMap<>();
            rateMap.put(url, map);
        }
        map.put(method, RateLimiter.create(permitsPerSecond));
    }

}
