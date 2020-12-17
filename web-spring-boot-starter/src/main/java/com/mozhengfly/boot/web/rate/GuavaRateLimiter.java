/**
 * @projectName data-synchronization
 * @package com.mozhengfly.boot.web.rate
 * @className com.mozhengfly.boot.web.rate.GuavaRateLimiter
 * @copyright Copyright 2020 Thunisoft, Inc. All rights reserved.
 */

package com.mozhengfly.boot.web.rate;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class GuavaRateLimiter implements IRateStrategy {

    private Map<String, RateLimiter> rateMap = new ConcurrentHashMap();

    @Override
    public boolean tryAcquire(String url) {
        return rateMap.get(url).tryAcquire();
    }

    @Override
    public void addRateLimit(String url, double permitsPerSecond) {
        rateMap.put(url, RateLimiter.create(permitsPerSecond));
    }
}
